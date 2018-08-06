package com.example.beardie.currencyholder.data

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.beardie.currencyholder.data.api.ExchangeApiService
import com.example.beardie.currencyholder.data.local.entity.Transaction
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.Okio
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsNull.notNullValue
import org.json.JSONObject
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import timber.log.Timber
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

@RunWith(JUnit4::class)
class ApiTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: ExchangeApiService

    private lateinit var mockWebServer: MockWebServer


    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .build()
                .create(ExchangeApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    private val latch = CountDownLatch(1)

    @Test
    fun fromRUB_TO_USD() {
        enqueueResponse("RUB_USD.json")
        var rate = 0.0
        service.getExchangeRate("RUB_USD", "ultra").enqueue(object : Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                latch.countDown()
            }
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                response.body()?.string()?.let {
                    val data = JSONObject(it)
                    rate = data.getDouble("RUB_USD")
                    latch.countDown()
                }
            }})
        val request = mockWebServer.takeRequest()
        latch.await()
        Assert.assertThat(request.path, `is`("/api/v6/convert?q=RUB_USD&compact=ultra"))
        Assert.assertEquals(0.015786, rate, 0.0001)
        print(rate)
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader
                .getResourceAsStream("api-response/$fileName")
        val source = Okio.buffer(Okio.source(inputStream))
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
                mockResponse
                        .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
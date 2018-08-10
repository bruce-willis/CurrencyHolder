package com.example.beardie.currencyholder.ui.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.beardie.currencyholder.R
import com.example.beardie.currencyholder.ui.Navigator
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_about.*
import java.util.concurrent.ThreadLocalRandom


class AboutFragment : DaggerFragment(), View.OnClickListener {

    private val listSmile = listOf("(＾▽＾)",
            "(◕‿◕)",
            "(✯◡✯)",
            "＼(＾▽＾)／",
            "(＠＾◡＾)")

    companion object {
        fun newInstance(): AboutFragment {
            return AboutFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? Navigator)?.initToolbar(R.string.about_toolbar_title)
        button.setOnClickListener(this)
        btn_send_email.setOnClickListener { view ->
            val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "iliailemkov@gmail.com", null))
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Application CurrencyHolder feedback")
            startActivity(Intent.createChooser(emailIntent, "Send email"))
        }
    }

    override fun onClick(view: View) {
        Toast.makeText(view.context, listSmile[ThreadLocalRandom.current().nextInt(0, 5)], Toast.LENGTH_SHORT).show()
    }
}

#  CurrencyHolder

## Continuous Integration (dev branch)
* CircleCI [![CircleCI](https://circleci.com/gh/bruce-willis/CurrencyHolder/tree/develop.svg?style=svg)](https://circleci.com/gh/bruce-willis/CurrencyHolder/tree/develop)
* AppCenter [![Build status](https://build.appcenter.ms/v0.1/apps/32fd3403-5123-42e1-bde1-3844c326966a/branches/develop/badge)](https://appcenter.ms)
* [![works badge](https://cdn.rawgit.com/nikku/works-on-my-machine/v0.2.0/badge.svg)](https://github.com/nikku/works-on-my-machine)

***

## Questions

* ### Room codestyle:
    * Codestyle:
        * @TypeConverters for whole db class or only for data class
        * primary key inside class
        ```kotlin
        @PrimaryKey(autoGenerate = true) val id: Long
        ```
        or inside entity attribute
        ```kotlin
        @Entity(primaryKeys = ["id"]
        ```
    * Relations or join

***

## TODO

* [ ] seed database at first run (problem with `s_balance_names.adapter`)
* [x] Repeat seeding if error
* [x] better support of periodic transaction
* [ ] maybe get rid of `long id` in favour `name` field
* [ ] Edit save values
* [ ] Modify design (convert to Yandex style)
* [ ] design for tablets
* [ ] unit tests
* [ ] ui tests
* [ ] release build
* [ ] analytics
* [ ] **better handling screen rotation**

***

## Used libraries

* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata)
* [LeakCanary](https://github.com/square/leakcanary)
* [Crashlytics](https://fabric.io/kits/android/crashlytics)
* [Dagger 2](https://google.github.io/dagger/)
* [Room](https://developer.android.com/topic/libraries/architecture/room)
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
* [Android Debug Database](https://github.com/amitshekhariitbhu/Android-Debug-Database)
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)

***

## Used materials

### LiveData ViewModel
* [Basic](https://medium.com/@taman.neupane/basic-example-of-livedata-and-viewmodel-14d5af922d0)
* [How use LiveData with Fragments](https://medium.com/citerus/lifecycle-fragments-backstack-f32e34f012d5)

### Dagger setup
* [Dagger setup with kotlin](https://code.luasoftware.com/tutorials/android/setup-dagger2-for-android-kotlin/)
* [DI](http://albertgao.xyz/2018/04/18/dependency-injection-on-android-with-dagger-android-and-kotlin/)
* [ViewModel with Dagger](http://www.albertgao.xyz/2018/05/22/3-ways-to-handle-view-model-creation-in-android-with-dagger-and-kotlin/)


### Room
* [Pre-populate Room database](https://android.jlelse.eu/pre-populate-room-database-6920f9acc870)
* [Get instance of db](https://github.com/googlesamples/android-sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/data/AppDatabase.kt#L39)
* [Use Dao inheritance to reduce the amount of boilerplate code](https://gist.github.com/florina-muntenescu/1c78858f286d196d545c038a71a3e864)
* [TimeConverter using kotlin](https://gist.github.com/tinmegali/d4a477785f01e57066915a44543db6ed) and [official docs for Java](https://developer.android.com/training/data-storage/room/referencing-data#type-converters). 
btw, why only one language for docs?. 
btw[2] [how to support timezones](https://medium.com/@chrisbanes/room-time-2b4cf9672b98)
* [Converter for enum](https://stackoverflow.com/a/51104802/6696410)
```kotlin
@Suppress("NOTHING_TO_INLINE")
private inline fun <T : Enum<T>> T.toInt(): Int = this.ordinal

private inline fun <reified T : Enum<T>> Int.toEnum(): T = enumValues<T>()[this]
```
```kotlin
@TypeConverter fun myEnumToTnt(value: MyEnum) = value.toInt()
@TypeConverter fun intToMyEnum(value: Int) = value.toEnum<MyEnum>()
```
* why it's good to give name to columns
> Declaring the column info allows for the renaming of variables without implementing a database migration, as the column name would not change.
[comment from google sample](https://github.com/googlesamples/android-sunflower/blob/master/app/src/main/java/com/google/samples/apps/sunflower/data/GardenPlanting.kt#L31)
* 

## UI
* [center `Toolbar` title](https://stackoverflow.com/a/38175403/6696410)
* [set custom font to `Toolbar` title](https://stackoverflow.com/a/45895693/6696410)
* [set style for `TextView` in code](https://stackoverflow.com/questions/3142067/android-set-style-in-code)
* [Create a navigation drawer](https://developer.android.com/training/implementing-navigation/nav-drawer)

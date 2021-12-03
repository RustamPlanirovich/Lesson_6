package retrofit.json

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl("https://dictionary.yandex.net")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val yandexDictionaryService = retrofit.create(YandexJsonDictionaryService::class.java)
fun main() {
    val response = yandexDictionaryService.lookup("en-ru", "fighter jet").execute()
    if (response.isSuccessful){
        val result = response.body()
        println(result!!.def[0].tr[0].text)
    }
}
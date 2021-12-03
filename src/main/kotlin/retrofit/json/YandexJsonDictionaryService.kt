package retrofit.json

import retrofit.dictionary.DicResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface YandexJsonDictionaryService {
    @GET("api/v1/dicservice.json/lookup?key=dict.1.1.20170402T202525Z.1ed8dd7c23205718.efda3a2c4bdf834b16909622280b20b0d60840a1")
    fun lookup(
//        @Query("key")
//        key: String,
        @Query("lang")
        lang: String,
        @Query("text")
        text: String
    ): Call<JsonDictionaryResult>
}
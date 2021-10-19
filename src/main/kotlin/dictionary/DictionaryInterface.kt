package es.unizar.webeng.hello.dictionary

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface DictionaryInterface {

    @GET
    fun searchTerm(@Url url: String): Call<ResponseBody>

}
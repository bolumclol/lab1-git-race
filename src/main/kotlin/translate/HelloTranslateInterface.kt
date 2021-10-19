package es.unizar.webeng.hello.translate

import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Headers
import retrofit2.http.Body

/**
 * This interface defines a collection of functions for 
 * calling the external API of translation.
 */
interface HelloTranslateInterface {
    /**
     * Note:
     * - Request method and URL specified in the annotation
     * - Callback for the parsed response is the last parameter
     */
    @Headers("Content-Type: application/json")
    @POST("translate")
    fun translate(@Body translation: Translation): Call<Translation>
}
package es.unizar.webeng.hello.translate

import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

data class Translation(
    val q: String,
    val source: String,
    val target: String,
    val format: String,
    val translatedText: String
)

class HelloTranslate(
    private var from: String = "auto",
    private var to: String = "en",
    private var format: String = "text"
) {

    fun translate(sourceText: String): String {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_TRANSLATE_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val service = retrofit.create(HelloTranslateInterface::class.java)
        val trans = Translation(sourceText, from, to, format, "")
        val translate = service.translate(trans)
        return translate.execute().body()!!.translatedText
    }

    companion object {
        private const val URL_TRANSLATE_API = "https://translate.argosopentech.com/"
    }
}
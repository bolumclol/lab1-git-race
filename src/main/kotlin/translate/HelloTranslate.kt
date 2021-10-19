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

	/**
    * This function uses an external API that translates a
	* source text from autodetected language to english.
    *
    * @param sourceText source text in any language
    * @return the @translatedText in english from the body of the API response.
    */
    fun translate(sourceText: String): String {
        val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create()
			
	    // Configuration for getting the JSON ready to make the call to 
		// the @URL_TRANSLATE_API 
        val retrofit = Retrofit.Builder()
            .baseUrl(URL_TRANSLATE_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
	    // Create the instance with the selected interface
        val service = retrofit.create(HelloTranslateInterface::class.java)
        val trans = Translation(sourceText, from, to, format, "")
		// Call the API for translation
        val translate = service.translate(trans)
        return translate.execute().body()!!.translatedText
    }

    companion object {
        private const val URL_TRANSLATE_API = "https://translate.argosopentech.com/"
    }
}
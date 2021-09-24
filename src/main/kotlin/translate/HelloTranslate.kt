package es.unizar.webeng.hello.translate
import com.google.gson.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Call
import retrofit2.Response

import es.unizar.webeng.hello.translate.HelloTranslateInterface
data class Translation(val q: String, val source: String,val target:String,val format:String,val translatedText :String )
class HelloTranslate{
    var  from:  String 
    var to: String 
    var format:String
    val URL_TRANSLATE_API = "https://translate.astian.org/"

    
    constructor(f:String,t:String){
        from=f
        to=t
        format="text"
    }
    constructor(t:String){
        from="auto"
        to=t
        format="text"
    }
    constructor(){
        from="auto"
        to="en"
        format="text"
    }
    public fun translate(sourceText:String):String {
        val gson=GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()
        val retrofit=Retrofit.Builder()
            .baseUrl(URL_TRANSLATE_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val service=retrofit.create(HelloTranslateInterface::class.java)
        val trans=Translation(sourceText, from, to,format,"")
        val translate=service.translate(trans)
        return translate.execute().body()!!.translatedText
    }
}
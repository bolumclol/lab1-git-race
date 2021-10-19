package es.unizar.webeng.hello.dictionary

import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiDictionary {
    /**
     * This function allows us to create a Retrofit object witch would be in charge
     * of the petitions
     *
     * @return the Retrofit object
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.dictionaryapi.dev/api/v2/entries/es/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    /**
     * This function allows us to parse the information retrieved from the endpoint
     *
     * @param terms JSONArray obtained via the API request
     * @return arrayTerm list with all the definitions for the queried word
     */
    private fun parseResponse(terms: JSONArray): Array<String> {
        val aux = terms.getJSONObject(0).getJSONArray("meanings")
        val definitions = aux.getJSONObject(0).getJSONArray("definitions")
        val maxLoop = definitions.length()
        val arrayTerm = Array(maxLoop) { "it = $it" }
        for (i in 0 until maxLoop) {
            arrayTerm[i] = definitions.getJSONObject(i).getString("definition")
        }
        return arrayTerm
    }

    /**
     * This function allows us to parse the information retrieved from the endpoint when
     * an error has occurred
     *
     * @param error JSONObject obtained throw the call
     * @return errorArray list with all the information retrieved from the server in order to
     * inform us about the received status code
     */
    private fun parseError(error: JSONObject): Array<String> {
        val maxLoop = error.length()
        val errorArray = Array(maxLoop) { "it = $it" }
        errorArray[0] = error.getString("message")
        errorArray[1] = error.getString("resolution")
        return errorArray
    }

    /**
     * This function contains all the logic to perform the query and also has some basic
     * error handling
     *
     * @param query String term who needs to be searched
     * @return DictionaryResponse Data structure that contains either error info
     * or array of returned definitions
     */
    fun searchDictionary(query: String): DictionaryResponse {
        val service = getRetrofit().create(DictionaryInterface::class.java)
        val serviceQuery = service.searchTerm(query)
        val call = serviceQuery.execute()
        return when (call.code()) {
            200 -> {
                val definitions = call.body()?.string()
                val test = JSONArray(definitions)
                val response = parseResponse(test)
                DictionaryResponse(
                    query,
                    call.code(),
                    response
                )
            }
            404, 500 -> {
                val error = call.errorBody()?.string()
                val err = JSONObject(error)
                val response = parseError(err)
                DictionaryResponse(
                    query,
                    call.code(),
                    response
                )
            }
            else ->
                DictionaryResponse(
                    query,
                    call.code(),
                    arrayOf("Ha ocurrido algo inesperado, intentelo mas tarde")
                )
        }

    }
}
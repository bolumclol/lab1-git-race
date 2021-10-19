package es.unizar.webeng.hello.dictionary

/**
 * Class where the structure of the returned information is defined for the API query
 */

data class DictionaryResponse(var term: String, var status: Int, var terms: Array<String>)

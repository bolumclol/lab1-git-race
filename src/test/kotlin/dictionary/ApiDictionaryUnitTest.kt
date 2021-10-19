package es.unizar.webeng.hello.dictionary


import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ApiDictionaryUnitTest {
    private lateinit var dictionary: ApiDictionary

    @BeforeEach
    fun setup() {
        dictionary = ApiDictionary()
    }

    @Test
    fun testCorrectSearchTerm() {
        // Check if the message is correctly translate from Spanish to English
        val message = dictionary.searchDictionary("patata")
        assertThat(message.term).isEqualTo("patata")
        assertThat(message.status).isEqualTo(200)
        assertThat(message.terms).isNotEmpty

    }

    @Test
    fun testIncorrectSearchTerm() {
        // Check if the message is correctly translate from Spanish to English
        val message = dictionary.searchDictionary("qtysusiusri6rd")
        assertThat(message.status).isEqualTo(404)
        assertThat(message.terms[0]).isEqualTo("Sorry pal, we couldn't find definitions for the word you were looking for.")
        assertThat(message.terms[1]).isEqualTo("You can try the search again at later time or head to the web instead.")
    }
}

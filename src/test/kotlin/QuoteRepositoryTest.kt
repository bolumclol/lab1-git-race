package es.unizar.webeng.hello

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

/**
 * The annotation @DataJpaTest specifies that this class has tests that focus
 * only on JPA components.
 * 
 * This class tests some functions of the QuoteRepository.
 */
@DataJpaTest
class QuoteRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val quoteRepository: QuoteRepository) {
    
    /**
     * This function checks that a Quote is saved and retrieved correctly.
     */
    @Test
    fun testFindById() {
        val quote = Quote(
            "If debugging is the process of removing software bugs, then programming must be the process of putting them in.",
            "Edsger W. Dijkstra")
        entityManager.persist(quote)
        entityManager.flush()
        val found = quoteRepository.findByIdOrNull(quote.id!!)
        assertThat(found).isEqualTo(quote)
    }

    /**
     * This function checks that the id retrieved by findMinId is that of the
     * first added quote.
     */
    @Test
    fun testFindMinId() {
        val quote1 = Quote(
            "If debugging is the process of removing software bugs, then programming must be the process of putting them in.",
            "Edsger W. Dijkstra")
        entityManager.persist(quote1)
        val quote2 = Quote(
            "Premature optimization is the root of all evil",
            "Donald Knuth")
        entityManager.persist(quote2)
        entityManager.flush()
        val found = quoteRepository.findMinId()
        assertThat(found).isEqualTo(quote1.id)
    }

    /**
     * This function checks that the id retrieved by findMaxId is that of the
     * last added quote.
     */
    @Test
    fun testFindMaxId() {
        val quote1 = Quote(
            "If debugging is the process of removing software bugs, then programming must be the process of putting them in.",
            "Edsger W. Dijkstra")
        entityManager.persist(quote1)
        val quote2 = Quote(
            "Premature optimization is the root of all evil",
            "Donald Knuth")
        entityManager.persist(quote2)
        entityManager.flush()
        val found = quoteRepository.findMaxId()
        assertThat(found).isEqualTo(quote2.id)
    }
}
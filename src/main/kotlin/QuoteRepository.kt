package es.unizar.webeng.hello

import org.springframework.data.repository.CrudRepository
import org.springframework.data.jpa.repository.Query

/**
 * This interface refines the CrudRepository one to save a collection of Quote
 * objects identified by a Long each and provides two new operations to retrieve
 * the min and max ids in the repository.
 */
interface QuoteRepository: CrudRepository<Quote, Long> {
    @Query("SELECT MIN(id) FROM Quote")
    fun findMinId(): Long?

    @Query("SELECT MAX(id) FROM Quote")
    fun findMaxId(): Long?
}
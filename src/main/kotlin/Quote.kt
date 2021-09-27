package es.unizar.webeng.hello

import javax.persistence.*

/**
 * The @Entity annotation marks the Quote class as an entity that will have a
 * mapping in the database.
 * The @Id annotation specifies that id is the primary key of the entity.
 * The @GeneratedValue annotation provides for automatic generation of the keys.
 * 
 * The Quote class represents a sentence associated with the person who said it.
 */
@Entity
class Quote(
		var quote: String,
		var saidBy: String,
		@Id @GeneratedValue var id: Long? = null)
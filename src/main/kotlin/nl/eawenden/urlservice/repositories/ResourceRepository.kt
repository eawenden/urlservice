package nl.eawenden.urlservice.repositories

import org.springframework.data.repository.CrudRepository

interface ResourceRepository : CrudRepository<Resource, String> {
    fun findByUrn(urn: String): Resource? = findById(urn).orElse(null)
}

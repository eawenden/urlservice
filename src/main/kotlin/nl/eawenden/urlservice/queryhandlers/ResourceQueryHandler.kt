package nl.eawenden.urlservice.queryhandlers

import nl.eawenden.urlservice.domain.queries.FindResourceByUrn
import nl.eawenden.urlservice.repositories.Resource
import nl.eawenden.urlservice.repositories.ResourceRepository
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class ResourceQueryHandler(val resourceRepository: ResourceRepository) {

    @QueryHandler
    fun handle(query: FindResourceByUrn): Resource? = resourceRepository.findByUrn(query.urn)
}

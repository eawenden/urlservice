package nl.eawenden.urlservice.eventhandlers

import nl.eawenden.urlservice.domain.ResourceAdded
import nl.eawenden.urlservice.domain.ResourceLocationChanged
import nl.eawenden.urlservice.repositories.Resource
import nl.eawenden.urlservice.repositories.ResourceRepository
import org.axonframework.eventhandling.EventHandler
import org.springframework.stereotype.Component

@Component
class ResourceProjectionEventHandler(val resourceRepository: ResourceRepository) {
    @EventHandler
    fun on(event: ResourceAdded) {
        resourceRepository.save(Resource(event.urn, event.location, event.canonicalLocation))
    }

    @EventHandler
    fun on(event: ResourceLocationChanged) {
        resourceRepository.save(Resource(event.urn, event.location, event.canonicalLocation))
    }
}

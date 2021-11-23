package nl.eawenden.urlservice.controllers

import nl.eawenden.urlservice.repositories.ResourceRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourceController(val resourceRepository: ResourceRepository) {

    @GetMapping("/resource/{urn}")
    fun get(@PathVariable urn: String) = resourceRepository.findByUrn(urn) ?: "Resource not found!"
}

package nl.eawenden.urlservice.controllers

import nl.eawenden.urlservice.domain.queries.FindResourceByUrn
import nl.eawenden.urlservice.repositories.Resource
import org.axonframework.extensions.kotlin.queryOptional
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ResourceController(val queryGateway: QueryGateway) {

    @GetMapping("/resource/{urn}")
    fun get(@PathVariable urn: String) = queryGateway.queryOptional<Resource, FindResourceByUrn>(FindResourceByUrn(urn)).join() ?: "Resource not found!"
}

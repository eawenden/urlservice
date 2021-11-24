package nl.eawenden.urlservice.domain

import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle.apply
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

import nl.eawenden.urlservice.domain.commands.*
import nl.eawenden.urlservice.domain.events.*

@Aggregate(type = "Resource")
class ResourceAggregate {

    @AggregateIdentifier
    private lateinit var urn: String
    private lateinit var location: String
    private lateinit var canonicalLocation: String

    @CommandHandler
    @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
    fun handle(command: AddResource) {
        if (!this::urn.isInitialized) {
            apply(ResourceAdded(command.urn, command.location, command.canonicalLocation))
        } else if (command.location != location || command.canonicalLocation != canonicalLocation) {
            apply(ResourceLocationChanged(command.urn, command.location, command.canonicalLocation))
        }
    }

    @EventSourcingHandler
    fun on(event: ResourceAdded) {
        urn = event.urn
        location = event.location
        canonicalLocation = event.canonicalLocation
    }

    @EventSourcingHandler
    fun on(event: ResourceLocationChanged) {
        urn = event.urn
        location = event.location
        canonicalLocation = event.canonicalLocation
    }
}

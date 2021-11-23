package nl.eawenden.urlservice.domain

import org.axonframework.modelling.command.TargetAggregateIdentifier

data class AddResource(@TargetAggregateIdentifier val urn: String, val location: String, val canonicalLocation: String)
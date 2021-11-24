package nl.eawenden.urlservice.domain.events

data class ResourceAdded(val urn: String, val location: String, val canonicalLocation: String)
data class ResourceLocationChanged(val urn: String, val location: String, val canonicalLocation: String)

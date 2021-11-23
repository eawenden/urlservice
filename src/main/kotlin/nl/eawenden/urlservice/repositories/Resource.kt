package nl.eawenden.urlservice.repositories

import javax.persistence.Entity
import javax.persistence.Id

@Entity
data class Resource(@Id var urn: String?, var location: String?, var canonicalLocation: String?)
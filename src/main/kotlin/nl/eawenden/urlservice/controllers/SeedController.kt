package nl.eawenden.urlservice.controllers

import nl.eawenden.urlservice.domain.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SeedController(val commandGateway: CommandGateway) {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    @GetMapping("/seed")
    fun seed() {
        logger.info("Seeding started")

        commandGateway.sendAndWait<AddResource>(AddResource(
            "urn:dpg:article:volkskrant:9214ab0",
            "https://www.volkskrant.nl/nieuws-achtergrond/dairo-antonio-usuga-de-drugsbaas-die-zijn-tegenstanders-liet-vierendelen~b9214ab0/",
            "https://www.volkskrant.nl/nieuws-achtergrond/dairo-antonio-usuga-de-drugsbaas-die-zijn-tegenstanders-liet-vierendelen~b9214ab0/"
        ))

        commandGateway.sendAndWait<AddResource>(AddResource(
            "urn:dpg:article:ad:516216e",
            "https://www.ad.nl/dossier-coronavirus/extra-coronamaatregelen-dreigen-op-vervroegde-persconferentie-alles-ligt-op-tafel~a516216e/",
            "https://www.ad.nl/dossier-coronavirus/extra-coronamaatregelen-dreigen-op-vervroegde-persconferentie-alles-ligt-op-tafel~a516216e/"
        ))

        commandGateway.sendAndWait<AddResource>(AddResource(
            "urn:dpg:article:ad:516216e",
            "https://www.ad.nl/updated-section/updated-slug~a516216e/",
            "https://www.ad.nl/updated-section/updated-slug~a516216e/"
        ))

        logger.info("Seeding finished")
    }
}

package nl.eawenden.urlservice.domain

import nl.eawenden.urlservice.domain.ResourceAggregateTest.Fixtures.toAddCommand
import nl.eawenden.urlservice.domain.ResourceAggregateTest.Fixtures.toAddedEvent
import nl.eawenden.urlservice.domain.ResourceAggregateTest.Fixtures.toChangedEvent
import nl.eawenden.urlservice.domain.commands.*
import nl.eawenden.urlservice.domain.events.*
import nl.eawenden.urlservice.repositories.Resource
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResourceAggregateTest {
    object Fixtures {
        val vkArticle = Resource(
            "urn:dpg:article:volkskrant:9214ab0",
            "https://www.volkskrant.nl/nieuws-achtergrond/dairo-antonio-usuga-de-drugsbaas-die-zijn-tegenstanders-liet-vierendelen~b9214ab0/",
            "https://www.volkskrant.nl/nieuws-achtergrond/dairo-antonio-usuga-de-drugsbaas-die-zijn-tegenstanders-liet-vierendelen~b9214ab0/"
        )

        val adArticle = Resource(
            "urn:dpg:article:ad:516216e",
            "https://www.ad.nl/dossier-coronavirus/extra-coronamaatregelen-dreigen-op-vervroegde-persconferentie-alles-ligt-op-tafel~a516216e/",
            "https://www.ad.nl/dossier-coronavirus/extra-coronamaatregelen-dreigen-op-vervroegde-persconferentie-alles-ligt-op-tafel~a516216e/"
        )

        val updatedAdArticle = Resource(
            "urn:dpg:article:ad:516216e",
            "https://www.ad.nl/updated-section/updated-slug~a516216e/",
            "https://www.ad.nl/updated-section/updated-slug~a516216e/"
        )

        fun Resource.toAddCommand(): AddResource = AddResource(this.urn!!, this.location!!, this.canonicalLocation!!)
        fun Resource.toAddedEvent(): ResourceAdded = ResourceAdded(this.urn!!, this.location!!, this.canonicalLocation!!)
        fun Resource.toChangedEvent(): ResourceLocationChanged = ResourceLocationChanged(this.urn!!, this.location!!, this.canonicalLocation!!)

    }

    private lateinit var fixture: FixtureConfiguration<ResourceAggregate>

    @BeforeEach
    fun setup() = run { fixture = AggregateTestFixture(ResourceAggregate::class.java) }

    @Test
    fun addResource() {
        fixture.givenNoPriorActivity()
            .`when`(Fixtures.vkArticle.toAddCommand())
            .expectSuccessfulHandlerExecution()
            .expectEvents(Fixtures.vkArticle.toAddedEvent())
    }

    @Test
    fun addingADuplicateResultShouldNotTriggerAnEvent() {
        fixture.given(Fixtures.vkArticle.toAddedEvent())
            .`when`(Fixtures.vkArticle.toAddCommand())
            .expectSuccessfulHandlerExecution()
            .expectNoEvents()
    }

    @Test
    fun changeLocationForExistingResource() {
        fixture.given(Fixtures.adArticle.toAddedEvent())
            .`when`(Fixtures.updatedAdArticle.toAddCommand())
            .expectSuccessfulHandlerExecution()
            .expectEvents(Fixtures.updatedAdArticle.toChangedEvent())
    }
}

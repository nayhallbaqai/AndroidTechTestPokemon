package io.deepmatter.pokemon.core

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.deepmatter.pokemon.model.Card
import io.deepmatter.pokemon.model.Rarity
import io.deepmatter.pokemon.util.random.Randomiser
import io.deepmatter.pokemon.viewmodel.Round
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoundFactoryImplTest {

    private lateinit var factory: RoundFactory

    @Mock
    private lateinit var randomiser: Randomiser

    @Before
    fun setup() {
        factory = RoundFactoryImpl(
            randomiser)
    }

    @Test
    fun `generates an empty round from empty list`() {
        val cards = emptyList<Card>()

        val expected = Round()
        assertThat(factory.buildRound(cards)).isEqualTo(expected)
    }

    @Test
    fun `generates an empty round from list of size one`() {
        val cards = listOf<Card>(
            common("A"))

        val expected = Round()
        assertThat(factory.buildRound(cards)).isEqualTo(expected)
    }

    @Test
    fun `generates a round with cards from two different rarities`() {
        val cards = listOf(
            common("A"),
            uncommon("B"),
            common("C"),
            common("D"))

        val expected = listOf(
            common("A"),
            uncommon("B"))

        whenever(randomiser.getIntInRange(any(), any()))
            .thenReturn(0)

        assertThat(factory.buildRound(cards).cards).isEqualTo(expected)
    }

    @Test
    fun `generates a round with cards from more than two different rarities `() {
        val cards = listOf(
            common("A"),
            uncommon("B"),
            rare("C"),
            common("D"),
            rare("E"),
            rareHolo("F"))

        val expected = listOf(
            rare("C"),
            common("D"))

        whenever(randomiser.getIntInRange(any(), any()))
            .thenReturn(2)

        assertThat(factory.buildRound(cards).cards).isEqualTo(expected)
    }

    @Test
    fun `uncommon wins over common`() {
        val cards = listOf(
            common("A"),
            uncommon("B"))

        val expected = uncommon("B")

        whenever(randomiser.getIntInRange(any(), any()))
            .thenReturn(0)

        assertThat(factory.buildRound(cards).winner).isEqualTo(expected)
    }

    @Test
    fun `builds empty round if cards of only one rarity are supplied`() {
        val cards = listOf(
            common("A"),
            common("B"))

        val expected = Round()

        assertThat(factory.buildRound(cards)).isEqualTo(expected)
    }

    @Test
    fun `getting the Rarest card`() {
        val cardOne = Card("One", Rarity.Rare)
        val cardTwo = Card("Two", Rarity.Uncommon)
        assertThat(factory.getRarestCard(cardOne, cardTwo)).isEqualTo(cardOne)
    }

    @Test
    fun `comparing when cardTwo is rarer then cardOne`(){
        val cardOne = Card("One", Rarity.Common)
        val cardTwo = Card("Two", Rarity.RareUltra)
        assertThat(factory.compareTo(cardOne, cardTwo)).isLessThan(0)
    }

    @Test
    fun `comparing when cardOne is rarer then cardTwo`(){
        val cardOne = Card("One", Rarity.RareUltra)
        val cardTwo = Card("Two", Rarity.Common)
        assertThat(factory.compareTo(cardOne, cardTwo)).isGreaterThan(0)
    }

    @Test
    fun `comparing when both cards have same rarity`(){
        val cardOne = Card("One", Rarity.Rare)
        val cardTwo = Card("Two", Rarity.Rare)
        assertThat(factory.compareTo(cardOne, cardTwo)).isEqualTo(0)
    }

    private fun common(image: String) = Card(
        image = image,
        rarity = Rarity.Common)

    private fun uncommon(image: String) = Card(
        image = image,
        rarity = Rarity.Uncommon)

    private fun rare(image: String) = Card(
        image = image,
        rarity = Rarity.Rare)

    private fun rareHolo(image: String) = Card(
        image = image,
        rarity = Rarity.RareHolo)
}
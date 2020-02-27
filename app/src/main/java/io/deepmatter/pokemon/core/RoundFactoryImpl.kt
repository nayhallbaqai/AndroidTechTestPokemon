package io.deepmatter.pokemon.core

import io.deepmatter.pokemon.model.Card
import io.deepmatter.pokemon.model.Rarity
import io.deepmatter.pokemon.util.random.Randomiser
import io.deepmatter.pokemon.viewmodel.Round

class RoundFactoryImpl(private val randomiser: Randomiser) : RoundFactory {

    private val mapRarities: Map<Rarity, Int> = mapOf(
        Rarity.Common to 1,
        Rarity.Uncommon to 2,
        Rarity.Rare to 3,
        Rarity.RareHolo to 4,
        Rarity.RareUltra to 5,
        Rarity.RareUltra to 6
    )

    override fun buildRound(cards: List<Card>): Round {

        if (cards.isEmpty()) {
            return Round()
        }

        var cardList = cards
        var randomNum: Int = randomiser.getIntInRange(0, cardList.size - 1)
        val cardOne: Card = cardList.get(randomNum)
        var cardTwo: Card = cardOne
        cardList = cardList.filterNot {compareTo(cardOne, it) == 0} // dropping all cards with rarity equal to cardOne

        while (compareTo(cardOne, cardTwo) == 0 && cardList.isNotEmpty()) {
            randomNum = randomiser.getIntInRange(0, cardList.size - 1)
            cardTwo = cardList.get(randomNum)
            cardList = cardList.filterNot {compareTo(cardTwo, it) == 0} // dropping all cards with rarity equal to cardTwo
        }

        if (compareTo(cardOne, cardTwo) == 0) {
            return Round()
        }

        val listOfCards = listOf(cardOne, cardTwo)
        val winnerCard: Card = getRarestCard(cardOne, cardTwo)

        return Round(listOfCards, winnerCard)
    }

    override fun getRarestCard(cardOne: Card, cardTwo: Card): Card {

        if(compareTo(cardOne, cardTwo) > 0) {
            return cardOne
        }
        return cardTwo
    }

    override fun compareTo(cardOne: Card, cardTwo: Card): Int {

        val cardOneRarity = mapRarities.getValue(cardOne.rarity)
        val cardTwoRarity = mapRarities.getValue(cardTwo.rarity)

        return cardOneRarity - cardTwoRarity
    }
}
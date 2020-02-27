package io.deepmatter.pokemon.core

import io.deepmatter.pokemon.model.Card
import io.deepmatter.pokemon.viewmodel.Round

interface RoundFactory {

    fun buildRound(cards: List<Card>): Round

    fun getRarestCard(cardOne: Card, cardTwo: Card): Card

    fun compareTo(cardOne: Card, cardTwo: Card): Int
}
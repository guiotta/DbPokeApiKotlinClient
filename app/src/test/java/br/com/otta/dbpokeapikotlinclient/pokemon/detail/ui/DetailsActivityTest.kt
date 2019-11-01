package br.com.otta.dbpokeapikotlinclient.pokemon.detail.ui

import android.widget.TextView
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.AbilityItem
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.PokemonDetailResponse
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsActivityTest {
    private val NAME: String = "name"
    private val HEIGHT: Int = 1
    private val WEIGHT: Int = 2

    @Mock
    private lateinit var nameView: TextView
    @Mock
    private lateinit var heightView: TextView
    @Mock
    private lateinit var weightView: TextView
    private var abilityItemA: AbilityItem = AbilityItem()
    private var abilityItemB: AbilityItem = AbilityItem()
    private var response: PokemonDetailResponse = PokemonDetailResponse(name = NAME, height = HEIGHT, weight = WEIGHT, abilities = listOf(abilityItemA, abilityItemB))


    @Test
    fun shouldCorrectlyUpdateTextViews() {
        val activity: DetailsActivity = DetailsActivity()

        activity.updateTextViews(nameView, heightView, weightView, response)

        Mockito.verify(nameView).text = NAME
        Mockito.verify(heightView).text = HEIGHT.toString()
        Mockito.verify(weightView).text = WEIGHT.toString()
    }

    @Test
    fun shouldCorrectlyCreateAdapter() {
        val activity: DetailsActivity = DetailsActivity()

        val actualValue = activity.createAdapter(response, activity)

        Assert.assertEquals(actualValue.items, listOf(abilityItemA, abilityItemB))
        Assert.assertEquals(actualValue.context, activity)
    }
}
package br.com.otta.dbpokeapikotlinclient.pokemon.list.ui

import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonItem
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PokemonListFragmentTest {
    private val pokemonItemA: PokemonItem = PokemonItem()
    private val pokemonItemB: PokemonItem = PokemonItem()

    @Test
    fun shouldCorrectlyCreateFragment() {
        val fragment: PokemonListFragment = PokemonListFragment.newInstance(arrayListOf(pokemonItemA, pokemonItemB))
        fragment.onCreate(fragment.arguments)

        Assert.assertEquals(arrayListOf(pokemonItemA, pokemonItemB), fragment.pokemonList)
    }
}
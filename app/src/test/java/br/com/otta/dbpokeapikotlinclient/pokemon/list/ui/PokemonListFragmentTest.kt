package br.com.otta.dbpokeapikotlinclient.pokemon.list.ui

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PokemonListFragmentTest {
    private val pokemonListUrl = "url"

    @Test
    fun shouldCorrectlyCreateFragment() {
        val fragment: PokemonListFragment = PokemonListFragment.newInstance(pokemonListUrl)
        fragment.onCreate(fragment.arguments)

        Assert.assertEquals("url", fragment.pokemonListUrl)
    }
}
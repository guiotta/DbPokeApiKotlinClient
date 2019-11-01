package br.com.otta.dbpokeapikotlinclient.type.ui

import br.com.otta.dbpokeapikotlinclient.type.model.Type
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PokemonTypeListFragmentTest {
    private val typeA: Type = Type()
    private val typeB: Type = Type()

    @Test
    fun shouldCorrectlyCreateFragment() {
        val fragment: PokemonTypeListFragment = PokemonTypeListFragment.newInstance(arrayListOf(typeA, typeB))
        fragment.onCreate(fragment.arguments)

        Assert.assertEquals(arrayListOf(typeA, typeB), fragment.pokemonTypeList)
    }
}
package br.com.otta.dbpokeapikotlinclient.type.adapter

import br.com.otta.dbpokeapikotlinclient.type.model.Type
import br.com.otta.dbpokeapikotlinclient.type.ui.PokemonTypeListFragment
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PokemonTypeListAdapterTest {
    @Mock
    private lateinit var listener: PokemonTypeListFragment.OnListFragmentInteractionListener
    private val typeA = Type("name", "url")
    private val typeB = Type("name", "url")


    @Test
    fun shouldGetListSizeForItemCount() {
        val adapter = PokemonTypeListAdapter(listOf(typeA, typeB), listener)

        Assert.assertEquals(2, adapter.itemCount)
    }
}
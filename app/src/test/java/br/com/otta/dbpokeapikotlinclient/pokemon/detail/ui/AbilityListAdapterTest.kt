package br.com.otta.dbpokeapikotlinclient.pokemon.detail.ui

import android.content.Context
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.AbilityItem
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AbilityListAdapterTest {
    @Mock
    private lateinit var context: Context
    private val abilityItemA: AbilityItem = AbilityItem()
    private val abilityIteB: AbilityItem = AbilityItem()

    @Test
    fun shouldGetListSizeForItemCount() {
        val adapter = AbilityListAdapter(listOf(abilityItemA, abilityIteB), context)

        Assert.assertEquals(2, adapter.itemCount)
    }

}
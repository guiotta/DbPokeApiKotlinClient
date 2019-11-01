package br.com.otta.dbpokeapikotlinclient.pokemon.detail.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import java.util.stream.Collectors

@JsonIgnoreProperties(ignoreUnknown = true)
data class PokemonDetailResponse(
    val abilities: List<AbilityItem> = emptyList(),
    val height: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val sprites: Sprite = Sprite()
) {

    fun toShareContent(): String {
        val abilitiesName = abilities.stream().map { abilityItem -> abilityItem.ability.name }.collect(Collectors.toList())
        return StringBuilder("Name: ")
            .append(name)
            .append(" Height: ")
            .append(height)
            .append(" Weight: ")
            .append(weight)
            .append(" Abilities: ")
            .append(abilitiesName)
            .toString()
    }
}
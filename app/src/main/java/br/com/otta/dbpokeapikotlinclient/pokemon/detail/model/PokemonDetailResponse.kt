package br.com.otta.dbpokeapikotlinclient.pokemon.detail.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PokemonDetailResponse(
    val abilities: List<AbilityItem> = emptyList(),
    val height: Int = 0,
    val name: String = "",
    val weight: Int = 0,
    val sprites: Sprite = Sprite()
) {
}
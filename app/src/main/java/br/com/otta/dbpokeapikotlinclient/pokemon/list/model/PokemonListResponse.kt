package br.com.otta.dbpokeapikotlinclient.pokemon.list.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PokemonListResponse(val pokemon: ArrayList<PokemonItem> = ArrayList<PokemonItem>()) {
}
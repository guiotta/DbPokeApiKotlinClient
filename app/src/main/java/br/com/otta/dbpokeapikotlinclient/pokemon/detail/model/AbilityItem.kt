package br.com.otta.dbpokeapikotlinclient.pokemon.detail.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class AbilityItem(val ability: Ability = Ability(), val slot: Int = 0) {
}
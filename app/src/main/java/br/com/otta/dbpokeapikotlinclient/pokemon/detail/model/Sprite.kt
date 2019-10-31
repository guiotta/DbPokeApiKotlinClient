package br.com.otta.dbpokeapikotlinclient.pokemon.detail.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Sprite(val front_default: String = "") {
}
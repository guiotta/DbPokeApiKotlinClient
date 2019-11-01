package br.com.otta.dbpokeapikotlinclient.pokemon.detail.service

import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.PokemonDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Interface para controlar o acesso a informações de detalhes de um Pokemon.
 */
interface PokemonDetailService {

    @GET
    fun call(@Url url: String): Call<PokemonDetailResponse>
}
package br.com.otta.dbpokeapikotlinclient.pokemon.detail

import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.PokemonDetailResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonDetailService {

    @GET
    fun call(@Url url: String): Call<PokemonDetailResponse>
}
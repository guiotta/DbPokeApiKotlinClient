package br.com.otta.dbpokeapikotlinclient.pokemon.list.service

import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface PokemonListService {

    @GET
    fun list(@Url url: String): Call<PokemonListResponse>
}
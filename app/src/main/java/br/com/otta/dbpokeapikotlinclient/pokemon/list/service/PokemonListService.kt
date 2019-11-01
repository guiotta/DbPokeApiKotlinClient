package br.com.otta.dbpokeapikotlinclient.pokemon.list.service

import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Interface para controlar o acesso a informações da lista de Pokemon de um tipo.
 */
interface PokemonListService {

    @GET
    fun list(@Url url: String): Call<PokemonListResponse>
}
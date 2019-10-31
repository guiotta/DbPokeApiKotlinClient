package br.com.otta.dbpokeapikotlinclient.configuration

import br.com.otta.dbpokeapikotlinclient.pokemon.detail.PokemonDetailService
import br.com.otta.dbpokeapikotlinclient.pokemon.list.service.PokemonListService
import br.com.otta.dbpokeapikotlinclient.type.service.TypesService
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

class RetrofitInitializer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(JacksonConverterFactory.create())
        .build()

    fun typesService() = retrofit.create(TypesService::class.java);
    fun pokemonListService() = retrofit.create(PokemonListService::class.java)
    fun pokemonDetailService() = retrofit.create(PokemonDetailService::class.java)
}
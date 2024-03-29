package br.com.otta.dbpokeapikotlinclient.type.service

import br.com.otta.dbpokeapikotlinclient.type.model.Type
import br.com.otta.dbpokeapikotlinclient.type.model.TypeResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Interface para controlar o acesso a informações de tipos de Pokemon.
 */
interface TypesService {

    @GET("type/")
    fun list() : Call<TypeResponse>
}
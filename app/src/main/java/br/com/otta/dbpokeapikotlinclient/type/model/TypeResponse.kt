package br.com.otta.dbpokeapikotlinclient.type.model

data class TypeResponse(val count: Int = 0, val next: String = "", val previous: String = "", val results: ArrayList<Type> = ArrayList()) {
}
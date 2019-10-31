package br.com.otta.dbpokeapikotlinclient.pokemon.list.model

import android.os.Parcel
import android.os.Parcelable

data class PokemonItem(val pokemon: Pokemon = Pokemon(), val slot: Int = 0): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readParcelable(Pokemon::class.java.classLoader),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeParcelable(pokemon, flags)
        parcel.writeInt(slot)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PokemonItem> {
        override fun createFromParcel(parcel: Parcel): PokemonItem {
            return PokemonItem(parcel)
        }

        override fun newArray(size: Int): Array<PokemonItem?> {
            return arrayOfNulls(size)
        }
    }
}
package br.com.otta.dbpokeapikotlinclient.pokemon.list.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonItem

import br.com.otta.dbpokeapikotlinclient.pokemon.list.ui.PokemonListFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_pokemon.view.*

/**
 * Classe com os comportamentos para o adapter contendo a lista de Pokemon de um determinado tipo, exibida em uma listagem própria.
 */
class PokemonListAdapter(
    private val pokemonItens: List<PokemonItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_pokemon, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pokemonItens[position]
        holder?.let {
            it.bindView(item)
        }
    }

    override fun getItemCount(): Int = pokemonItens.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        fun bindView(pokemonItem: PokemonItem) {
            val name = mView.pokemon_name_item

            name.text = pokemonItem.pokemon.name

            itemView.setOnClickListener {
                mListener?.openDetailsActivity(pokemonItem.pokemon.url)
            }
        }
    }
}

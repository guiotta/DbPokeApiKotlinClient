package br.com.otta.dbpokeapikotlinclient.type.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.otta.dbpokeapikotlinclient.type.ui.PokemonTypeListFragment.OnListFragmentInteractionListener
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonListResponse
import br.com.otta.dbpokeapikotlinclient.type.model.Type

import kotlinx.android.synthetic.main.fragment_type.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Classe com os comportamentos para o adapter contendo a lista de tipos de Pokemon, exibida em uma listagem própria.
 */
class PokemonTypeListAdapter(private val types: List<Type>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<PokemonTypeListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = types[position]
        holder?.let {
            it.bindView(type)
        }
    }

    override fun getItemCount(): Int = types.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(type: Type) {
            val name = itemView.type_name_item

            name.text = type.name

            itemView.setOnClickListener {
                mListener?.updateFragmentContent(type.url)
            }
        }
    }
}

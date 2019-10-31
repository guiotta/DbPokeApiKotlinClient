package br.com.otta.dbpokeapikotlinclient.pokemon.list.ui

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.PokemonDetailResponse
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonItem


import br.com.otta.dbpokeapikotlinclient.pokemon.list.ui.PokemonListFragment.OnListFragmentInteractionListener
import br.com.otta.dbpokeapikotlinclient.pokemon.list.ui.dummy.DummyContent.DummyItem

import kotlinx.android.synthetic.main.fragment_pokemon.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PokemonListAdapter(
    private val pokemonItens: List<PokemonItem>,
    private val mListener: OnListFragmentInteractionListener?
) : RecyclerView.Adapter<PokemonListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

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
                /*val call = RetrofitInitializer().pokemonDetailService().call(pokemonItem.pokemon.url)

                call.enqueue(object : Callback<PokemonDetailResponse> {
                    override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                        Log.e("onFailure error", t?.message)
                    }

                    override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                        response?.body()?.let {
                            Log.i("Pokemon response", it.toString())
                        }
                    }
                })*/
            }
        }
    }
}

package br.com.otta.dbpokeapikotlinclient.type.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.otta.dbpokeapikotlinclient.type.ui.PokemonTypeListFragment.OnListFragmentInteractionListener
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.dummy.DummyContent.DummyItem
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonListResponse
import br.com.otta.dbpokeapikotlinclient.type.model.Type
import br.com.otta.dbpokeapikotlinclient.type.ui.PokemonTypeListFragment

import kotlinx.android.synthetic.main.fragment_type.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PokemonTypeListAdapter(private val types: List<Type>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<PokemonTypeListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            //val item = v.tag as DummyItem
            //mListener?.onListFragmentInteraction(item)
        }
    }

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
                val call = RetrofitInitializer().pokemonListService().list(type.url);
                call.enqueue(
                    object : Callback<PokemonListResponse> {
                        override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                            Log.e("onFailure error", t?.message)
                        }

                        override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                            response?.body()?.let {
                                Log.i("Pokemon response", it.toString())
                                val pokemonListResponse = it.pokemon;
                                mListener?.onListFragmentInteraction(pokemonListResponse)
                            }
                        }
                    }
                )
            }
        }
    }
}
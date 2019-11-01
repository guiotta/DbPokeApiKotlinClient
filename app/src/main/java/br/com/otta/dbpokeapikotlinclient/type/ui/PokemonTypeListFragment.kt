package br.com.otta.dbpokeapikotlinclient.type.ui

import android.content.Context
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.otta.dbpokeapikotlinclient.R

import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonItem
import br.com.otta.dbpokeapikotlinclient.type.adapter.PokemonTypeListAdapter
import br.com.otta.dbpokeapikotlinclient.type.model.Type

/**
 * Fragmento para controlar a exibição da lista de Tipos de Pokemon exibida ao inciar a aplicação.
 */
class PokemonTypeListFragment : Fragment() {
    @VisibleForTesting
    var pokemonTypeList: ArrayList<Type> = ArrayList()
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            pokemonTypeList = it.getParcelableArrayList(ARG_POKEMON_TYPE_LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_type_list, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.list)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = PokemonTypeListAdapter(pokemonTypeList, listener)

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnListFragmentInteractionListener {
        fun updateFragmentContent(pokemonListUrl: String)
    }

    companion object {
        const val ARG_POKEMON_TYPE_LIST = "pokemon-type-list"

        @JvmStatic
        fun newInstance(pokemonTypeList: ArrayList<Type>) =
            PokemonTypeListFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(ARG_POKEMON_TYPE_LIST, pokemonTypeList)
                }
            }
    }
}

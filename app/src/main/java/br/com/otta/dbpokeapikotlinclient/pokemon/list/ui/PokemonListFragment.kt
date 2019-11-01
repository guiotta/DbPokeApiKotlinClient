package br.com.otta.dbpokeapikotlinclient.pokemon.list.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PokemonListFragment.OnListFragmentInteractionListener] interface.
 */
class PokemonListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var pokemonList: ArrayList<PokemonItem> = ArrayList()

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            pokemonList = it.getParcelableArrayList(ARG_POKEMON_LIST)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.list)
        recycler.layoutManager = LinearLayoutManager(context)
        recycler.adapter = PokemonListAdapter(pokemonList, listener)

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
        fun openDetailsActivity(pokemonDetail: String)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_POKEMON_LIST = "pokemon-list"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int, pokemonList: ArrayList<PokemonItem>) =
            PokemonListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putParcelableArrayList(ARG_POKEMON_LIST, pokemonList)
                }
            }
    }
}

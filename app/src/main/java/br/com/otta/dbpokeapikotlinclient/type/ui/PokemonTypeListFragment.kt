package br.com.otta.dbpokeapikotlinclient.type.ui

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
import br.com.otta.dbpokeapikotlinclient.type.adapter.PokemonTypeListAdapter
import br.com.otta.dbpokeapikotlinclient.type.model.Type

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PokemonTypeListFragment.OnListFragmentInteractionListener] interface.
 */
class PokemonTypeListFragment : Fragment() {

    // TODO: Customize parameters
    private var columnCount = 1
    private var pokemonTypeList: ArrayList<Type> = ArrayList()

    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun updateFragmentContent(item: ArrayList<PokemonItem>)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_POKEMON_TYPE_LIST = "pokemon-type-list"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int, pokemonTypeList: ArrayList<Type>) =
            PokemonTypeListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                    putParcelableArrayList(ARG_POKEMON_TYPE_LIST, pokemonTypeList)
                }
            }
    }
}

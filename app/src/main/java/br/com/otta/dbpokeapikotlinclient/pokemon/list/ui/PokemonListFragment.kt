package br.com.otta.dbpokeapikotlinclient.pokemon.list.ui

import android.content.Context
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [PokemonListFragment.OnListFragmentInteractionListener] interface.
 */
class PokemonListFragment : Fragment() {
    @VisibleForTesting
    var pokemonListUrl: String = ""
    private var listener: OnListFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            pokemonListUrl = it.getString(ARG_POKEMON_URL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        val recycler = view.findViewById<RecyclerView>(R.id.list)

        val progress = view.findViewById<ProgressBar>(R.id.pokemon_list_progress)
        progress.visibility = View.VISIBLE

        val call = RetrofitInitializer().pokemonListService().list(pokemonListUrl);
        call.enqueue(
            object : Callback<PokemonListResponse> {
                override fun onFailure(call: Call<PokemonListResponse>, t: Throwable) {
                    Toast.makeText(view.context, R.string.network_error, Toast.LENGTH_LONG).show()
                    progress.visibility = View.GONE
                    Log.e("onFailure error", t?.message)
                }

                override fun onResponse(call: Call<PokemonListResponse>, response: Response<PokemonListResponse>) {
                    response?.body()?.let {
                        Log.i("Pokemon response", it.toString())
                        recycler.layoutManager = LinearLayoutManager(context)
                        recycler.adapter = PokemonListAdapter(it.pokemon, listener)
                        progress.visibility = View.GONE
                    }
                }
            }
        )

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
        const val ARG_POKEMON_URL = "pokemon-url"

        @JvmStatic
        fun newInstance(pokemonUrl: String) =
            PokemonListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_POKEMON_URL, pokemonUrl)
                }
            }
    }
}

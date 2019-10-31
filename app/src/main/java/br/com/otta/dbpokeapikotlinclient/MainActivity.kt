package br.com.otta.dbpokeapikotlinclient

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonItem
import br.com.otta.dbpokeapikotlinclient.pokemon.list.ui.PokemonListFragment
import br.com.otta.dbpokeapikotlinclient.pokemon.list.ui.dummy.DummyContent
import br.com.otta.dbpokeapikotlinclient.type.model.Type
import br.com.otta.dbpokeapikotlinclient.type.model.TypeResponse
import br.com.otta.dbpokeapikotlinclient.type.ui.PokemonTypeListFragment
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), PokemonTypeListFragment.OnListFragmentInteractionListener,
    PokemonListFragment.OnListFragmentInteractionListener {
    val BACK_STACK_TAG: String = "pokemonListTag"

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun updateFragmentContent(item: ArrayList<PokemonItem>) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment, PokemonListFragment.newInstance(1, item))
            .addToBackStack(BACK_STACK_TAG)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = type_list_progress
        progressBar.visibility = View.VISIBLE
        val call = RetrofitInitializer().typesService().list()

        call.enqueue(
            object : Callback<TypeResponse?> {
                override fun onResponse(call: Call<TypeResponse?>?, response: Response<TypeResponse?>?) {
                    response?.body()?.let {
                        progressBar.visibility = View.GONE
                        val types : ArrayList<Type> = it.results
                        //configureList(types)

                        if (savedInstanceState == null) {
                            supportFragmentManager
                                .beginTransaction()
                                .add(R.id.main_fragment, PokemonTypeListFragment.newInstance(1, types))
                                .commit()
                        }
                    }
                }
                override fun onFailure(call: Call<TypeResponse?>?, t: Throwable) {
                    progressBar.visibility = View.GONE
                    Log.e("onFailure error", t?.message)
                }
            })
    }

    private fun configureList(types: List<Type>) {
        //val recyclerView = type_list_recyclerview
        //recyclerView.adapter = TypeListAdapter(types, this)
        //val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        //recyclerView.layoutManager = layoutManager
    }
}

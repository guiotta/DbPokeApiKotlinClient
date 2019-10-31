package br.com.otta.dbpokeapikotlinclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.View
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.dummy.DummyContent
import br.com.otta.dbpokeapikotlinclient.type.adapter.TypeListAdapter
import br.com.otta.dbpokeapikotlinclient.type.model.Type
import br.com.otta.dbpokeapikotlinclient.type.model.TypeResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), PokemonTypeListFragment.OnListFragmentInteractionListener {
    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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

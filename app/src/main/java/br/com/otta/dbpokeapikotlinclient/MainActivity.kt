package br.com.otta.dbpokeapikotlinclient

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.ui.DetailsActivity
import br.com.otta.dbpokeapikotlinclient.pokemon.list.model.PokemonItem
import br.com.otta.dbpokeapikotlinclient.pokemon.list.ui.PokemonListFragment
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

    override fun openDetailsActivity(pokemonDetail: String) {
        val intent = Intent(this, DetailsActivity::class.java)
        startActivityWithIntent(this, intent, pokemonDetail)
    }

    override fun updateFragmentContent(item: ArrayList<PokemonItem>) {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_fragment, PokemonListFragment.newInstance(item))
            .addToBackStack(BACK_STACK_TAG)
            .commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressBar = type_list_progress
        updateProgressBarVisibility(progressBar, View.VISIBLE)
        val call = RetrofitInitializer().typesService().list()

        call.enqueue(
            object : Callback<TypeResponse?> {
                override fun onResponse(call: Call<TypeResponse?>?, response: Response<TypeResponse?>?) {
                    response?.body()?.let {
                        updateProgressBarVisibility(progressBar, View.GONE)
                        val types : ArrayList<Type> = it.results

                        if (savedInstanceState == null) {
                            supportFragmentManager
                                .beginTransaction()
                                .add(R.id.main_fragment, PokemonTypeListFragment.newInstance(types))
                                .commit()
                        }
                    }
                }
                override fun onFailure(call: Call<TypeResponse?>?, t: Throwable) {
                    updateProgressBarVisibility(progressBar, View.GONE)
                    Log.e("onFailure error", t?.message)
                }
            })
    }

    @VisibleForTesting
    fun startActivityWithIntent(activity: Activity, intent: Intent, pokemonUrl: String) {
        intent.putExtra(DetailsActivity.URL_TAG, pokemonUrl)
        activity.startActivity(intent)
    }

    @VisibleForTesting
    fun updateProgressBarVisibility(progressBar: ProgressBar, visibility: Int): Int {
        progressBar.visibility = visibility
        return progressBar.visibility
    }
}

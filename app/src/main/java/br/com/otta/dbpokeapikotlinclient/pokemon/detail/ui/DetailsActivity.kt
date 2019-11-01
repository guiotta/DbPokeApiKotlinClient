package br.com.otta.dbpokeapikotlinclient.pokemon.detail.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.PokemonDetailResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsActivity : AppCompatActivity() {
    val SHARE_TYPE: String = "text/plain"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.com.otta.dbpokeapikotlinclient.R.layout.activity_details)
        setSupportActionBar(toolbar)

        val url = intent.getStringExtra("url")
        var pokemonResponse: PokemonDetailResponse? = PokemonDetailResponse();

        val call = RetrofitInitializer().pokemonDetailService().call(url)

        call.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                response?.body()?.let {
                    pokemonResponse = it;
                    val labelName = label_name_pokemon
                    val labelHeight = label_height_pokemon
                    val labelWeight = label_weight_pokemon
                    val imgPokemon = pokemon_img
                    val abilitiesList = abilities_list

                    labelName.text = it.name
                    labelHeight.text = it.height.toString()
                    labelWeight.text = it.weight.toString()

                    abilitiesList.layoutManager = LinearLayoutManager(this@DetailsActivity)
                    abilitiesList.adapter = AbilityListAdapter(it.abilities, this@DetailsActivity)

                    Picasso.get()
                        .load(it.sprites.front_default)
                        .resize(500, 500)
                        .centerCrop()
                        .into(imgPokemon)
                    Log.i("Pokemon response", it.toString())
                }
            }
        })

        fab.setOnClickListener { view ->
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type = SHARE_TYPE
            shareIntent.putExtra(Intent.EXTRA_TEXT, pokemonResponse?.toShareContent());
            startActivity(Intent.createChooser(shareIntent, getString(R.string.send_to)))
        }
    }
}

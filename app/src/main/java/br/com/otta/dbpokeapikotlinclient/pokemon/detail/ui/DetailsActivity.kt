package br.com.otta.dbpokeapikotlinclient.pokemon.detail.ui

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.configuration.RetrofitInitializer
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.PokemonDetailResponse
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_details.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import android.os.Looper
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.os.Handler


class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(br.com.otta.dbpokeapikotlinclient.R.layout.activity_details)
        setSupportActionBar(toolbar)

        val url = intent.getStringExtra("url")

        val call = RetrofitInitializer().pokemonDetailService().call(url)

        call.enqueue(object : Callback<PokemonDetailResponse> {
            override fun onFailure(call: Call<PokemonDetailResponse>, t: Throwable) {
                Log.e("onFailure error", t?.message)
            }

            override fun onResponse(call: Call<PokemonDetailResponse>, response: Response<PokemonDetailResponse>) {
                response?.body()?.let {
                    val labelName = label_name_pokemon
                    val labelHeight = label_height_pokemon
                    val labelWeight = label_weight_pokemon
                    val imgPokemon = pokemon_img

                    labelName.text = it.name
                    labelHeight.text = it.height.toString()
                    labelWeight.text = it.weight.toString()

                    val request = Request.Builder().url(it.sprites.front_default).build()
                    val call = OkHttpClient.Builder().build().newCall(request)
                    call.enqueue(
                        object : okhttp3.Callback {
                            override fun onFailure(call: okhttp3.Call, e: IOException) {
                            }

                            override fun onResponse(call: okhttp3.Call, response: okhttp3.Response) {
                                if (response.isSuccessful) {
                                    val bitmap = BitmapFactory.decodeStream(response.body()?.byteStream())
                                    // Remember to set the bitmap in the main thread.
                                    Handler(Looper.getMainLooper()).post(Runnable { imgPokemon.setImageBitmap(bitmap) })
                                } else {
                                    //Handle the error
                                }
                            }

                        }
                    )
                    Log.i("Pokemon response", it.toString())
                }
            }
        })

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }
}

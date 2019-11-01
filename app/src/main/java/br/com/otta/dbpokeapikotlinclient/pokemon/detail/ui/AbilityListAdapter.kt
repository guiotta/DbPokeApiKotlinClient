package br.com.otta.dbpokeapikotlinclient.pokemon.detail.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.pokemon.detail.model.AbilityItem
import kotlinx.android.synthetic.main.ability_list_item.view.*

/**
 * Classe com os comportamentos para o adapter contendo a lista de habilidades de um Pokemon, exibida na tela de detalhes.
 */
class AbilityListAdapter (val items : List<AbilityItem>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.ability_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items.get(position)
        holder.name?.text = item.ability.name
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val name = view.ability_name
}
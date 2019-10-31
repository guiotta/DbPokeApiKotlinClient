package br.com.otta.dbpokeapikotlinclient.type.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.type.model.Type
import kotlinx.android.synthetic.main.type_list_item.view.*

class TypeListAdapter(private val types: List<Type>, private val context: Context) : RecyclerView.Adapter<TypeListAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.type_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = types[position]
        holder?.let {
            it.bindView(type)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(type: Type) {
            val name = itemView.type_name_item1

            name.text = type.name
        }
    }
}
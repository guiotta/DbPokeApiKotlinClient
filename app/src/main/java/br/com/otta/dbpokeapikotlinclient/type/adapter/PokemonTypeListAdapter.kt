package br.com.otta.dbpokeapikotlinclient.type.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import br.com.otta.dbpokeapikotlinclient.type.ui.PokemonTypeListFragment.OnListFragmentInteractionListener
import br.com.otta.dbpokeapikotlinclient.R
import br.com.otta.dbpokeapikotlinclient.dummy.DummyContent.DummyItem
import br.com.otta.dbpokeapikotlinclient.type.model.Type

import kotlinx.android.synthetic.main.fragment_type.view.*

/**
 * [RecyclerView.Adapter] that can display a [DummyItem] and makes a call to the
 * specified [OnListFragmentInteractionListener].
 * TODO: Replace the implementation with code for your data type.
 */
class PokemonTypeListAdapter(private val types: List<Type>, private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<PokemonTypeListAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DummyItem
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_type, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val type = types[position]
        holder?.let {
            it.bindView(type)
        }
    }

    override fun getItemCount(): Int = types.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(type: Type) {
            val name = itemView.type_name_item

            name.text = type.name
        }
    }
}

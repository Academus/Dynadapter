package io.academus.dynadapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DynAdapter(var context: Context, var items: List<DynItem>) : RecyclerView.Adapter<DynHolder>() {
    init {
        setHasStableIds(true)
    }

    override fun onBindViewHolder(holder: DynHolder, position: Int) = items[position].onBindView(context, position, items, holder.itemView)
    override fun getItemViewType(position: Int): Int = items[position].getLayout()
    override fun getItemId(position: Int): Long = items[position].getId()
    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DynHolder {
        val view = LayoutInflater.from(parent.context).inflate(viewType, parent, false)
        return DynHolder(view)
    }
}
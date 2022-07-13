package com.example.ca

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CoinModel

class Adapter(
    val items: List<CoinModel>,
    val onClick: (String,Int)->Unit
    ): RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView=
            LayoutInflater.from(parent.context).inflate(R.layout.itemholder,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            onClick(items[position].id,items[position].rank)
        }
        holder.setId(items[position].id)
        holder.setName(items[position].name)
        holder.setSymbol(items[position].symbol)
        holder.setRank(items[position].rank.toString())
        holder.setIsNew(items[position].is_new.toString())
        holder.setIsActive(items[position].is_active.toString())
    }

    override fun getItemCount(): Int {
        return items.size
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val id=view.findViewById<TextView>(R.id.id)
        private val name=view.findViewById<TextView>(R.id.name)
        private val symbol=view.findViewById<TextView>(R.id.symbol)
        private val rank=view.findViewById<TextView>(R.id.rank)
        private val is_new=view.findViewById<TextView>(R.id.isNew)
        private val is_active=view.findViewById<TextView>(R.id.isActive)
        fun setId(a:String)
        {
            id.text=a
        }
        fun setName(a:String)
        {
            name.text=a
        }
        fun setSymbol(a:String)
        {
            symbol.text=a
        }
        fun setRank(a:String)
        {
            rank.text=a
        }
        fun setIsNew(a:String)
        {
            is_new.text=a
        }
        fun setIsActive(a:String)
        {
            is_active.text=a
        }
        init {
            view.setOnClickListener{
                Toast.makeText(view.context, "되나?",Toast.LENGTH_SHORT).show()
            }
        }

    }

}
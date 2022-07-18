package com.example.ca

import android.graphics.Color
import android.graphics.Color.RED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.CoinModel

class Adapter(
    val items: List<CoinModel>,
    val onClick: (String)->Unit
    ): RecyclerView.Adapter<Adapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflatedView=
            LayoutInflater.from(parent.context).inflate(R.layout.itemholder,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener{
            onClick(items[position].id)
        }
        holder.setId(items[position].id)
        holder.setName(items[position].name)
        holder.setSymbol("("+items[position].symbol+")")
        holder.setRank(items[position].rank.toString())
        holder.setIsNew(items[position].is_new)
        holder.setIsActive(items[position].is_active)
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
        fun setIsNew(a:Boolean)
        {
            if(a==false)
            {is_new.visibility=View.INVISIBLE}
            else
            {
                is_new.visibility=View.VISIBLE
            }
        }
        fun setIsActive(a:Boolean)
        {
            if(a==true)
            {is_active.text="active"}
            else
            {
                is_active.setTextColor(Color.RED)
                is_active.text="inactive"
            }

        }


    }

}
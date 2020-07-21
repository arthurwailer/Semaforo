package com.example.semaforo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contacto_recycler.view.*

class AdaptadorCustom(items:ArrayList<ModeloDatoRecycler>):RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {
    var viewHolder:ViewHolder? = null
    var items:ArrayList<ModeloDatoRecycler>? = null
    init{
        Log.i("IMPORTANTE", items.count().toString())
        this.items = items
    }

    // infla el loyout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.contacto_recycler,parent,false)
        viewHolder = ViewHolder(vista)
        return viewHolder!!

    }



    override fun getItemCount(): Int {
         return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.nombre?.text = item?.nombre


    }

    class ViewHolder(vista: View): RecyclerView.ViewHolder(vista){
        var vista = vista
        var nombre:TextView? = null
        init{
            nombre = vista.tvNombre
        }

    }
}
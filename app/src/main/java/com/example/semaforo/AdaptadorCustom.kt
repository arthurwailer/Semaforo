package com.example.semaforo

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.contacto_recycler.view.*

class AdaptadorCustom(items:ArrayList<ModeloDatoRecycler>, var clickListener: ClickListener):RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {
    var viewHolder:ViewHolder? = null
    var items:ArrayList<ModeloDatoRecycler>? = null
    init{
        Log.i("IMPORTANTE", items.count().toString())
        this.items = items
    }

    // infla el loyout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.contacto_recycler,parent,false)
        viewHolder = ViewHolder(vista, clickListener)
        return viewHolder!!

    }



    override fun getItemCount(): Int {
         return this.items?.count()!!
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.nombre?.text = item?.nombre


    }

    class ViewHolder(vista: View, listener: ClickListener): RecyclerView.ViewHolder(vista), View.OnClickListener{
        var vista = vista
        var nombre:TextView? = null
        var listener:ClickListener?=null

        init{
            nombre = vista.tvNombre

            this.listener = listener

            this.vista.setOnClickListener {
                onClickSemaforo(this.vista)
            }

            this.vista.editButton.setOnClickListener {
                onClickEdit(this.vista)
            }

            this.vista.deleteButton.setOnClickListener {
                onClickDelete(this.vista)
            }
        }

        fun onClickSemaforo(p0: View?) {
            this.listener?.onClickSemaforo(p0!!, adapterPosition)
        }

        fun onClickEdit(p0: View?) {
            this.listener?.onClickEdit(p0!!, adapterPosition)
        }

        fun onClickDelete(p0: View?) {
            this.listener?.onClickDelete(p0!!, adapterPosition)
        }

        override fun onClick(p0: View?) {
            Log.i("INFO:- ","Test message")
        }

    }
}
package com.example.semaforo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var contactos: ArrayList<ModeloDatoRecycler>? = null


    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptadorCustom: AdaptadorCustom? = null
    var lista:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        contactos = ArrayList()
        contactos?.add(ModeloDatoRecycler("alonso"))
        contactos?.add(ModeloDatoRecycler("pericles"))
        contactos?.add(ModeloDatoRecycler("sim"))
        contactos?.add(ModeloDatoRecycler("Josefa"))
        contactos?.add(ModeloDatoRecycler("Josefa6"))
        contactos?.add(ModeloDatoRecycler("alonso"))
        contactos?.add(ModeloDatoRecycler("pericles"))
        contactos?.add(ModeloDatoRecycler("sim"))
        contactos?.add(ModeloDatoRecycler("Josefa"))
        contactos?.add(ModeloDatoRecycler("Josefa6"))

        Log.i("", contactos!![0].toString())

        lista = findViewById(R.id.layoutRecycler)

        layoutManager = LinearLayoutManager(this)
        adaptadorCustom = AdaptadorCustom(contactos!!)
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptadorCustom




        botonAgregar.setOnClickListener {
            val intent = Intent(this, formulario::class.java)
            Log.i("hoasdjl","ajsdh")
            startActivity(intent)
        }

    }
}
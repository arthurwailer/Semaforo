package com.example.semaforo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal lateinit var  db:SQLiteHelper
    var contactos: ArrayList<ModeloDatoRecycler>? = ArrayList<ModeloDatoRecycler>()
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptadorCustom: AdaptadorCustom? = null
    var lista:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = SQLiteHelper(this) // creo un obejto de tipo SQLiteHelper
        db.allPerson // este metodo retorna los nombres de la columna Nanme de la BBDD

        lista = findViewById(R.id.layoutRecycler)
        layoutManager = LinearLayoutManager(this)
        adaptadorCustom = AdaptadorCustom(db.allPerson as ArrayList<ModeloDatoRecycler>) // entrego la lista al adaptador custom
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptadorCustom

        botonAgregar.setOnClickListener {
            val intent = Intent(this, formulario::class.java)
            Log.i("hoasdjl","ajsdh")
            startActivity(intent)
        }

    }
}
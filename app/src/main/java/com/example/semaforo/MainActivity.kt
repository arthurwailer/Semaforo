package com.example.semaforo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
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
        contactos = ArrayList(db.allPerson) // este metodo retorna los nombres de la columna Nanme de la BBDD

        lista = findViewById(R.id.layoutRecycler)
        layoutManager = LinearLayoutManager(this)
        adaptadorCustom = AdaptadorCustom(db.allPerson as ArrayList<ModeloDatoRecycler>,object:ClickListener{
            override fun onClickSemaforo(vista: View, posicion: Int) {

                Snackbar.make(vista, "Mostrar este semaforo - id:" + contactos?.get(posicion)?.id, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
            override fun onClickEdit(vista: View, posicion: Int) {
                Snackbar.make(vista, "Editar a " + contactos?.get(posicion)?.nombre, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
            override fun onClickDelete(vista: View, posicion: Int) {
                Snackbar.make(vista, "Borrar a " + contactos?.get(posicion)?.nombre, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        }) // entrego la lista al adaptador custom
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptadorCustom

        botonAgregar.setOnClickListener {
            val intent = Intent(this, formulario::class.java)
            Log.i("hoasdjl","ajsdh")
            startActivity(intent)
        }

    }
}
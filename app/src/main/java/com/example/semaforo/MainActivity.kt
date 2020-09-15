package com.example.semaforo

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.semaforo.AdaptadorCustom.ViewHolder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal lateinit var  db:SQLiteHelper
    private lateinit var deleteIcon:Drawable
    var contactos: ArrayList<ModeloDatoRecycler>? = ArrayList<ModeloDatoRecycler>()
    var contactosId : ArrayList<Int>? = ArrayList<Int>()
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptadorCustom: AdaptadorCustom? = null
    var lista:RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = SQLiteHelper(this) // creo un obejto de tipo SQLiteHelper
        contactos = ArrayList(db.allPerson) // este metodo retorna los nombres de la columna Nanme de la BBDD
        contactosId = ArrayList(db.allId)
        lista = findViewById(R.id.layoutRecycler)
        layoutManager = LinearLayoutManager(this)

        val actionbar = supportActionBar
        actionbar!!.title = "Equipos" // text de la parte d arriba de la actividad


        adaptadorCustom = AdaptadorCustom(db.allPerson as ArrayList<ModeloDatoRecycler>,object:ClickListener{
            override fun onClickSemaforo(vista: View, posicion: Int) {
            /**    contactos?.get(posicion)?.let { goToSemaforo(it,posicion, contactosId!!) } // obtengo el id de estas posicion **/
                goToSemaforo(posicion, contactosId!!)// mando la posición actual junto con el Array de ID de contacto
            }
            override fun onClickEdit(vista: View, posicion: Int) {
                contactos?.get(posicion)?.id?.let { editContacto(it) }
            }
            override fun onClickDelete(vista: View, posicion: Int) {
                Toast.makeText(applicationContext,"Contacto eliminado: "+ contactos?.get(posicion)?.id.toString(), Toast.LENGTH_SHORT).show()
                db.deletePerson(contactos?.get(posicion)?.id!!.toString())
                var intent = Intent(this@MainActivity,MainActivity::class.java)
                startActivity(intent)
            }

        }) // entrego la lista al adaptador custom
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptadorCustom

        botonAgregar.setOnClickListener {
            val intent = Intent(this, formulario::class.java)
            startActivity(intent)
        }
    }

    private fun editContacto(idContacto: Int) {
        val intent = Intent(this, formulario::class.java)
        intent.putExtra("ID_CONTACTO",idContacto)
        startActivity(intent)
    }

    private fun goToSemaforo(pos: Int, arrayList: ArrayList<Int> ) {
        val intent = Intent(this, SemaforoActivity::class.java)
        intent.putExtra("ARRAY",arrayList)
        intent.putExtra("POS_ARRAY",pos)
        startActivity(intent)
    }


}
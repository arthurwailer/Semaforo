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
    var layoutManager: RecyclerView.LayoutManager? = null
    var adaptadorCustom: AdaptadorCustom? = null
    var lista:RecyclerView? = null
    var colorDrawable:ColorDrawable= ColorDrawable(Color.parseColor("#FF0000"))

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = SQLiteHelper(this) // creo un obejto de tipo SQLiteHelper
        contactos = ArrayList(db.allPerson) // este metodo retorna los nombres de la columna Nanme de la BBDD
        lista = findViewById(R.id.layoutRecycler)
        layoutManager = LinearLayoutManager(this)
        deleteIcon = ContextCompat.getDrawable(this,R.drawable.ic_delete_black_18dp)!!

        val actionbar = supportActionBar
        actionbar!!.title = "Equipos" // text de la parte d arriba de la actividad


        adaptadorCustom = AdaptadorCustom(db.allPerson as ArrayList<ModeloDatoRecycler>,object:ClickListener{
            override fun onClickSemaforo(vista: View, posicion: Int) {
                Snackbar.make(vista, "Semaforo de " + contactos?.get(posicion)?.id, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
                contactos?.get(posicion)?.id?.let { goToSemaforo(it) } // obtengo el id de estas posicion
            }
            override fun onClickEdit(vista: View, posicion: Int) {
                Snackbar.make(vista, "Editar a " + contactos?.get(posicion)?.nombre, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
                contactos?.get(posicion)?.id?.let { editContacto(it) }
            }
            override fun onClickDelete(vista: View, posicion: Int) {
                Snackbar.make(vista, "Borrar a " + contactos?.get(posicion)?.id.toString(), Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
               db.deletePerson(contactos?.get(posicion)?.id!!.toString())
                var intent = Intent(this@MainActivity,MainActivity::class.java)
                startActivity(intent)
            }


        }) // entrego la lista al adaptador custom
        lista?.layoutManager = layoutManager
        lista?.adapter = adaptadorCustom


        val itemTouchHelperCallbacks: ItemTouchHelper.SimpleCallback = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, posicion: Int) {
                //db.deletePerson(contactos?.get(posicion)?.id.toString())// no se borra de la base datos
                (adaptadorCustom as AdaptadorCustom).removeItem(viewHolder)
            }


            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val itemView = viewHolder.itemView
                val iconMargin = (itemView.height - deleteIcon.intrinsicHeight)/2
                if (dX > 0){
                    //colorDrawable.setBounds(itemView.right,itemView.top,dX.toInt(),itemView.bottom)

                    return  //colorDrawable.setBounds(itemView.left,itemView.top,dX.toInt(),itemView.bottom)

                }else{
                    colorDrawable.setBounds(itemView.right + dX.toInt(),itemView.top,itemView.right,itemView.bottom)
                    //deleteIcon.setBounds(itemView.right+dX.toInt()+iconMargin,itemView.top+iconMargin,itemView.right-iconMargin,itemView.bottom-iconMargin)

                }
                colorDrawable.draw(c)
                //deleteIcon.draw(c)

                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive
                )
            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallbacks)
        itemTouchHelper.attachToRecyclerView(layoutRecycler)




        botonAgregar.setOnClickListener {
            val intent = Intent(this, formulario::class.java)
            Log.i("hoasdjl","ajsdh")
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onPause() {
        super.onPause()
    }

    private fun editContacto(idContacto: Int) {
        val intent = Intent(this, formulario::class.java)
        intent.putExtra("ID_CONTACTO",idContacto)
        startActivity(intent)
    }

    private fun goToSemaforo(idContacto: Int) {
        val intent = Intent(this, SemaforoActivity::class.java)
        intent.putExtra("ID_CONTACTO",idContacto)
        startActivity(intent)
    }


}
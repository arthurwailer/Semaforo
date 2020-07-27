package com.example.semaforo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_semaforo.*

class SemaforoActivity : AppCompatActivity() {
    internal lateinit var  db:SQLiteHelper
    private var extIdVal:Int? = null
    var contacto:ModeloContacto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_semaforo)

        extIdVal = intent?.getIntExtra("ID_CONTACTO", -1)

        Log.i("EXTERNAL VAL :-", extIdVal.toString())
        extIdVal?.let {
            db = SQLiteHelper(this) // creo un obejto de tipo SQLiteHelper
            contacto = db.getSingleResult(extIdVal!!)
            nameContact.text = contacto?.mName
            contacto?.mName?.let { it1 -> Log.i("NAME CONTACTO", it1 )}
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        lightRed.setOnClickListener {
            contacto?.mSmsRojoOff?.let { it1 ->
                Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        }

        lightOrange.setOnClickListener {
            contacto?.mSmsNaranjoOff?.let { it1 ->
                Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        }

        lightGreen.setOnClickListener {
            contacto?.mSmsVerdeOff?.let { it1 ->
                Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show()
            }
        }
    }

}
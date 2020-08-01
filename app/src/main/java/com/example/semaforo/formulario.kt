package com.example.semaforo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_formulario.*
import kotlinx.android.synthetic.main.activity_semaforo.*

class formulario : AppCompatActivity() {
    internal lateinit var  db:SQLiteHelper
    private var extIdVal:Int? = null
    var contacto:ModeloContacto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        extIdVal = intent?.getIntExtra("ID_CONTACTO", -1) ?: -1

        Log.i("EXTERNAL VAL :-", extIdVal.toString())
        if (extIdVal!! > 0) {
            db = SQLiteHelper(this) // creo un obejto de tipo SQLiteHelper
            contacto = db.getSingleResult(extIdVal!!)
            setValues(contacto!!);
            Toast.makeText(applicationContext,"Editando a "+contacto!!.mName!!,Toast.LENGTH_SHORT).show()// MENSAJE DE CONFIRMACION
        } else {
            Toast.makeText(applicationContext,"Nuevo Contacto",Toast.LENGTH_SHORT).show()
        }

        botonAtrasHome.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        botonGuardar.setOnClickListener{
            val sqLiteHelper = SQLiteHelper(this)

            val nombre = personName.text.toString() // INPUT DATA
            val telefono = personPhone.text.toString()
            val rojoOn = textSmsRojoOn.text.toString()
            val rojoOff = textSmsRojoOff.text.toString()
            val naranjoOn = textSmsNaranjoOn.text.toString()
            val naranjoOff = textSmsNaranjoOff.text.toString()
            val verdeOn = textSmsVerdeOn.text.toString()
            val verdeOff = textSmsVerdeOff.text.toString()

            val modeloContacto = ModeloContacto(nombre,telefono.toInt(),rojoOn,rojoOff,naranjoOn,naranjoOff,verdeOn,verdeOff,false,false,false)

            sqLiteHelper.insertData(modeloContacto) // TERMINAR DE INSERTAR DATA
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }
    }

    private fun setValues(contacto: ModeloContacto) {
        personName.setText(contacto.mName)
        personPhone.setText(contacto.mPhone.toString())
    }
}
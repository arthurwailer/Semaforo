package com.example.semaforo

import android.content.Intent
import android.graphics.PointF.length
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

        val actionbar = supportActionBar //boton atras a home
        actionbar!!.title = "Formulario"
        actionbar.setDisplayHomeAsUpEnabled(true)



        if (extIdVal!! > 0) { // si el id del contacto existe
            db = SQLiteHelper(this) // creo un obejto de tipo SQLiteHelper
            contacto = db.getSingleResult(extIdVal!!)
            setValues(contacto!!) // setea
            Toast.makeText(applicationContext,"Editando a "+contacto!!.mName!!,Toast.LENGTH_SHORT).show()// MENSAJE DE CONFIRMACION
        } else { // si no existe
            Toast.makeText(applicationContext,"Nuevo Contacto",Toast.LENGTH_SHORT).show()
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

            if (extIdVal!! > 0) {
                modeloContacto.mId = contacto!!.mId!!
                if (telefono.length == 8){
                    sqLiteHelper.updatePerson(modeloContacto)
                    Toast.makeText(applicationContext,"Actualizado",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Ingresa un numero de 8 digitos",Toast.LENGTH_SHORT).show()// MENSAJE DE CONFIRMACION
                }
            } else {
                if (telefono.length == 8) {
                    sqLiteHelper.insertData(modeloContacto) // TERMINAR DE INSERTAR DATA
                    Toast.makeText(applicationContext,"Contacto Guardado",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(applicationContext,"Ingresa un numero de 8 digitos",Toast.LENGTH_SHORT).show()//

                }
            }

        }
    }


    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

    private fun setValues(contacto: ModeloContacto) {
        personName.setText(contacto.mName)
        personPhone.setText(contacto.mPhone.toString())
        textSmsRojoOn.setText(contacto.mSmsRojoOn.toString())
        textSmsRojoOff.setText(contacto.mSmsRojoOff.toString())
        textSmsNaranjoOn.setText(contacto.mSmsNaranjoOn.toString())
        textSmsNaranjoOff.setText(contacto.mSmsNaranjoOff.toString())
        textSmsVerdeOn.setText(contacto.mSmsVerdeOn.toString())
        textSmsVerdeOff.setText(contacto.mSmsVerdeOff.toString())

    }
}
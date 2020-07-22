package com.example.semaforo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_formulario.*

class formulario : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)
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

            val modeloContacto = ModeloContacto(nombre,telefono.toInt(),rojoOn,rojoOff,naranjoOn,naranjoOff,verdeOn,verdeOff)

            sqLiteHelper.insertData(modeloContacto) // TERMINAR DE INSERTAR DATA
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)

        }


    }
}
package com.example.semaforo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.semaforo.R.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_semaforo.*

class SemaforoActivity : AppCompatActivity() {
    internal lateinit var  db:SQLiteHelper
    private var extIdVal:Int? = null
    var contacto:ModeloContacto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_semaforo)

        extIdVal = intent?.getIntExtra("ID_CONTACTO", -1)

        Log.i("EXTERNAL VAL :-", extIdVal.toString())
        extIdVal?.let {
            db = SQLiteHelper(this) // creo un obejto de tipo SQLiteHelper
            contacto = db.getSingleResult(extIdVal!!)
            nameContact.text = contacto?.mName
            contacto?.mName?.let { it1 -> Log.i("NAME CONTACTO", it1 )}
        }
        if (contacto?.mStatusRojo!!) {
            // Cambio de color de fondo a cada luz del semaforo (API 16 compatible)
            ViewCompat.setBackgroundTintList(
                lightRed,
                ContextCompat.getColorStateList(
                    getApplicationContext(),
                    color.lightOnRed
                )
            )
        } else {
            ViewCompat.setBackgroundTintList(
                lightRed,
                ContextCompat.getColorStateList(
                    getApplicationContext(),
                    color.lightOffRed
                )
            )
        }
        if (contacto?.mStatusNaranjo!!) {
            // Cambio de color de fondo a cada luz del semaforo (API 16 compatible)
            ViewCompat.setBackgroundTintList(
                lightOrange,
                ContextCompat.getColorStateList(
                    getApplicationContext(),
                    color.lightOnOrange
                )
            )
        } else {
            ViewCompat.setBackgroundTintList(
                lightOrange,
                ContextCompat.getColorStateList(
                    getApplicationContext(),
                    color.lightOffOrange
                )
            )
        }
        if (contacto?.mStatusVerde!!) {
            // Cambio de color de fondo a cada luz del semaforo (API 16 compatible)
            ViewCompat.setBackgroundTintList(
                lightGreen,
                ContextCompat.getColorStateList(
                    getApplicationContext(),
                    color.lightOnGreen
                )
            )
        } else {
            ViewCompat.setBackgroundTintList(
                lightGreen,
                ContextCompat.getColorStateList(
                    getApplicationContext(),
                    color.lightOffGreen
                )
            )
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        lightRed.setOnClickListener {
            if (contacto?.mStatusRojo!!) {
                contacto?.mSmsRojoOff?.let { it1 ->
                    Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    // Cambio de color de fondo a cada luz del semaforo (API 16 compatible)
                    ViewCompat.setBackgroundTintList(
                        lightRed,
                        ContextCompat.getColorStateList(
                            getApplicationContext(),
                            color.lightOffRed
                        )
                    )
                }
                contacto!!.mStatusRojo = false
            } else {
                contacto?.mSmsRojoOn?.let { it1 ->
                    Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    ViewCompat.setBackgroundTintList(
                        lightRed,
                        ContextCompat.getColorStateList(
                            getApplicationContext(),
                            color.lightOnRed
                        )
                    )
                }
                contacto!!.mStatusRojo = true
            }
            db.updateStatusColor(contacto!!.mId!!, "red", contacto!!.mStatusRojo!!)
        }

        lightOrange.setOnClickListener {
            if (contacto?.mStatusNaranjo!!) {
                contacto?.mSmsNaranjoOff?.let { it1 ->
                    Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    ViewCompat.setBackgroundTintList(
                        lightOrange,
                        ContextCompat.getColorStateList(
                            getApplicationContext(),
                            color.lightOffOrange
                        )
                    )
                }
                contacto!!.mStatusNaranjo = false
            } else {
                contacto?.mSmsNaranjoOn?.let { it1 ->
                    Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    ViewCompat.setBackgroundTintList(
                        lightOrange,
                        ContextCompat.getColorStateList(
                            getApplicationContext(),
                            color.lightOnOrange
                        )
                    )
                }
                contacto!!.mStatusNaranjo = true
            }
            db.updateStatusColor(contacto!!.mId!!, "orange", contacto!!.mStatusNaranjo!!)
        }

        lightGreen.setOnClickListener {
            if (contacto?.mStatusVerde!!) {
                contacto?.mSmsVerdeOff?.let { it1 ->
                    Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()

                    ViewCompat.setBackgroundTintList(
                        lightGreen,
                        ContextCompat.getColorStateList(
                            getApplicationContext(),
                            color.lightOffGreen
                        )
                    )
                }
                contacto!!.mStatusVerde = false
            } else {
                contacto?.mSmsVerdeOn?.let { it1 ->
                    Snackbar.make(view, it1, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show()
                    ViewCompat.setBackgroundTintList(
                        lightGreen,
                        ContextCompat.getColorStateList(
                            getApplicationContext(),
                            color.lightOnGreen
                        )
                    )
                }
                contacto!!.mStatusVerde = true
            }
            db.updateStatusColor(contacto!!.mId!!, "green", contacto!!.mStatusVerde!!)
        }
    }

}
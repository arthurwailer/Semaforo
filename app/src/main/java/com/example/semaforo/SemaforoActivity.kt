package com.example.semaforo

import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.telephony.SmsManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import com.example.semaforo.R.color
import com.example.semaforo.R.layout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_semaforo.*
import java.util.jar.Manifest

class SemaforoActivity : AppCompatActivity() {
    internal lateinit var  db:SQLiteHelper
    private var extIdVal:Int? = null
    var contacto:ModeloContacto? = null
    companion object {
        const val MY_PERMISSIONS_REQUEST_SEND_SMS = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_semaforo)

        val actionbar = supportActionBar
        actionbar!!.title = "Semaforo"
        actionbar.setDisplayHomeAsUpEnabled(true)

        if (ActivityCompat.checkSelfPermission(this,"android.permission.SEND_SMS")
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf("android.permission.SEND_SMS"),
                MY_PERMISSIONS_REQUEST_SEND_SMS)
        }

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
            ViewCompat.setBackgroundTintList(lightRed, ContextCompat.getColorStateList(
                getApplicationContext(), color.lightOnRed))
        } else {
            ViewCompat.setBackgroundTintList(lightRed, ContextCompat.getColorStateList(
                getApplicationContext(), color.lightOffRed))
        }
        if (contacto?.mStatusNaranjo!!) {
            // Cambio de color de fondo a cada luz del semaforo (API 16 compatible)
            ViewCompat.setBackgroundTintList(lightOrange, ContextCompat.getColorStateList(
                getApplicationContext(), color.lightOnOrange))
        } else {
            ViewCompat.setBackgroundTintList(lightOrange, ContextCompat.getColorStateList(
                getApplicationContext(), color.lightOffOrange))
        }
        if (contacto?.mStatusVerde!!) {
            // Cambio de color de fondo a cada luz del semaforo (API 16 compatible)
            ViewCompat.setBackgroundTintList(lightGreen, ContextCompat.getColorStateList(
                getApplicationContext(), color.lightOnGreen))
        } else {
            ViewCompat.setBackgroundTintList(lightGreen, ContextCompat.getColorStateList(
                getApplicationContext(), color.lightOffGreen))
        }
    }
    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()
        return true
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        lightRed.setOnClickListener {
            if (contacto?.mStatusRojo!!) {
                contacto?.mSmsRojoOff?.let { it1 ->
                    sendSMS(it1,contacto?.mPhone!!)
                    // Cambio de color de fondo a cada luz del semaforo (API 16 compatible)
                    ViewCompat.setBackgroundTintList(lightRed, ContextCompat.getColorStateList(
                        getApplicationContext(), color.lightOffRed))
                }
                contacto!!.mStatusRojo = false
            } else {
                contacto?.mSmsRojoOn?.let { it1 ->
                    sendSMS(it1,contacto?.mPhone!!)
                    ViewCompat.setBackgroundTintList(lightRed, ContextCompat.getColorStateList(
                        getApplicationContext(), color.lightOnRed))
                }
                contacto!!.mStatusRojo = true
            }
            db.updateStatusColor(contacto!!.mId!!, "red", contacto!!.mStatusRojo!!)
        }

        lightOrange.setOnClickListener {
            if (contacto?.mStatusNaranjo!!) {
                contacto?.mSmsNaranjoOff?.let { it1 ->
                    sendSMS(it1,contacto?.mPhone!!)
                    ViewCompat.setBackgroundTintList(lightOrange, ContextCompat.getColorStateList(
                        getApplicationContext(), color.lightOffOrange))
                }
                contacto!!.mStatusNaranjo = false
            } else {
                contacto?.mSmsNaranjoOn?.let { it1 ->
                    sendSMS(it1,contacto?.mPhone!!)
                    ViewCompat.setBackgroundTintList(lightOrange, ContextCompat.getColorStateList(
                        getApplicationContext(), color.lightOnOrange))
                }
                contacto!!.mStatusNaranjo = true
            }
            db.updateStatusColor(contacto!!.mId!!, "orange", contacto!!.mStatusNaranjo!!)
        }

        lightGreen.setOnClickListener {
            if (contacto?.mStatusVerde!!) {
                contacto?.mSmsVerdeOff?.let { it1 ->
                    sendSMS(it1,contacto?.mPhone!!)
                    ViewCompat.setBackgroundTintList(lightGreen, ContextCompat.getColorStateList(
                        getApplicationContext(), color.lightOffGreen))
                }
                contacto!!.mStatusVerde = false
            } else {
                contacto?.mSmsVerdeOn?.let { it1 ->
                    sendSMS(it1,contacto?.mPhone!!)
                    ViewCompat.setBackgroundTintList(lightGreen, ContextCompat.getColorStateList(
                        getApplicationContext(), color.lightOnGreen))
                }
                contacto!!.mStatusVerde = true
            }
            db.updateStatusColor(contacto!!.mId!!, "green", contacto!!.mStatusVerde!!)
        }
    }

    private fun sendSMS(msg:String,phone:Int) {
        try {
            val sms = SmsManager.getDefault()
            sms.sendTextMessage("+569"+phone,null,
                msg,null,null)
            Snackbar.make(view, "SMS ENVIADO: "+msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
        } catch (e: Exception) {
            Snackbar.make(view, "No Pudo Enviar SMS", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show()
            e.printStackTrace()
        }
    }
}
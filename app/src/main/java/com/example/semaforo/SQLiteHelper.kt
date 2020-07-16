package com.example.semaforo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import java.net.IDN

class SQLiteHelper: SQLiteOpenHelper {

    companion object{
        const val DATABASE_NAME = "contactos.db"
        const val DATABASE_VERSION = 1


        private const val TABLE_NAME = "tbl_contactos"
        private const val ID = "_id"
        private const val NAME:String = "Name"
        private const val PHONE = "Telephone"
        private const val SMS_ROJO:String = "sms_rojo"
        private const val SMS_NARANJO:String = "sms_naranjo"
        private const val SMS_VERDE:String = "sms_verde"

        // conts para crear la tabla en OnCreate
        const val CREATE_TABLE:String = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" "+ " (" +
                ID + " INTEGER PRIMARY KEY, "+
                NAME + " TEXT NOT NULL, "+
                PHONE + " INTEGER NOT NULL, "+
                SMS_ROJO + " TEXT NOT NULL, "+
                SMS_NARANJO +" TEXT NOT NULL, "+
                SMS_VERDE + " TEXT NOT NULL);"

    }

    var context:Context

    constructor(context: Context):super(context, DATABASE_NAME, null, DATABASE_VERSION){
    this.context = context
    }

    override fun onCreate(database: SQLiteDatabase?) {
        database!!.execSQL(CREATE_TABLE)

    }

    override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int) {
        database!!.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME)
        onCreate(database)
    }

    fun insertData(modeloContacto: ModeloContacto){
        val values = ContentValues()
        val db = this.writableDatabase

        values.put(NAME, modeloContacto.mName)
        values.put(PHONE, modeloContacto.mPhone)
        values.put(SMS_ROJO, modeloContacto.mSmsRojo)
        values.put(SMS_NARANJO, modeloContacto.mSmsNaranjo)
        values.put(SMS_VERDE, modeloContacto.mSmsVerde)

        db.insert(TABLE_NAME,null, values)
        Toast.makeText(context,"Contacto guardado",Toast.LENGTH_SHORT).show()// MENSAJE DE CONFIRMACION
        db.close()
    }
}
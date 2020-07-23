package com.example.semaforo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class SQLiteHelper: SQLiteOpenHelper {


    companion object{
        const val DATABASE_NAME = "contactos.db"
        const val DATABASE_VERSION = 1


        private const val TABLE_NAME = "tbl_contactos"
        private const val ID = "_id"
        private const val NAME:String = "Name"
        private const val PHONE = "Telephone"
        private const val SMS_ROJO_ON:String = "sms_rojo_on"
        private const val SMS_ROJO_OFF:String = "sms_rojo_off"
        private const val SMS_NARANJO_ON:String = "sms_naranjo_on"
        private const val SMS_NARANJO_OFF:String = "sms_naranjo_off"
        private const val SMS_VERDE_ON:String = "sms_verde_on"
        private const val SMS_VERDE_OFF:String = "sms_verde_off"
        private const val STATUS_ROJO = "status_rojo"
        private const val STATUS_NARANJO = "status_naranjo"
        private const val STATUS_VERDE = "status_verde"

        // conts para crear la tabla en OnCreate
        const val CREATE_TABLE:String = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" " + " (" +
                ID + " INTEGER PRIMARY KEY, "+
                NAME + " TEXT NOT NULL, "+
                PHONE + " INTEGER NOT NULL, "+
                SMS_ROJO_ON + " TEXT NOT NULL, "+
                SMS_ROJO_OFF + " TEXT NOT NULL, "+
                SMS_NARANJO_ON +" TEXT NOT NULL, "+
                SMS_NARANJO_OFF +" TEXT NOT NULL, "+
                SMS_VERDE_ON + " TEXT NOT NULL, "+
                SMS_VERDE_OFF + " TEXT NOT NULL, "+
                STATUS_ROJO + " BOOLEAN NOT NULL, "+
                STATUS_NARANJO + " BOOLEAN NOT NULL, "+
                STATUS_VERDE + " BOOLEAN NOT NULL );"


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
        values.put(SMS_ROJO_ON, modeloContacto.mSmsRojoOn)
        values.put(SMS_ROJO_OFF, modeloContacto.mSmsRojoOff)
        values.put(SMS_NARANJO_ON, modeloContacto.mSmsNaranjoOn)
        values.put(SMS_NARANJO_OFF, modeloContacto.mSmsNaranjoOff)
        values.put(SMS_VERDE_ON, modeloContacto.mSmsVerdeOn)
        values.put(SMS_VERDE_OFF, modeloContacto.mSmsVerdeOff)
        values.put(STATUS_ROJO,false)
        values.put(STATUS_NARANJO, false)
        values.put(STATUS_VERDE, false)

        db.insert(TABLE_NAME,null, values)
        Toast.makeText(context,"Contacto guardado",Toast.LENGTH_SHORT).show()// MENSAJE DE CONFIRMACION
        db.close()
    }


    val allPerson:List<ModeloDatoRecycler>
        get() {
            var lstContacto = ArrayList<ModeloDatoRecycler>()
            var selectQuery = "SELECT * FROM $TABLE_NAME"
            val db: SQLiteDatabase? = this.writableDatabase
            val cursor = db?.rawQuery(selectQuery,null)
            if(cursor!!.moveToFirst()){
                do{
                    val contacto = ModeloDatoRecycler(null)
                    contacto.nombre = cursor.getString(cursor.getColumnIndex(NAME))
                    lstContacto.add(contacto)
                }while(cursor.moveToNext())
            }
            db.close()
            return lstContacto
        }


}
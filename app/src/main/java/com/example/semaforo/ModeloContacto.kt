package com.example.semaforo

class ModeloContacto {

    var mId:Int? = null // en la funcion no se toma en cuenta por que es un autoincrement

    var mName:String? = null
    var mPhone:Int? = null
    var mSmsRojo:String? = null
    var mSmsNaranjo:String? = null
    var mSmsVerde:String? = null

    constructor(
        mName: String?,
        mPhone: Int?,
        mSmsRojo: String?,
        mSmsNaranjo: String?,
        mSmsVerde: String?
    ) {
        this.mName = mName
        this.mPhone = mPhone
        this.mSmsRojo = mSmsRojo
        this.mSmsNaranjo = mSmsNaranjo
        this.mSmsVerde = mSmsVerde
    }
}
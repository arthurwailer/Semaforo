package com.example.semaforo

class ModeloContacto {

    var mId:Int? = null // en la funcion no se toma en cuenta por que es un autoincrement

    var mName:String? = null
    var mPhone:Int? = null
    var mSmsRojoOn:String? = null
    var mSmsRojoOff:String? = null
    var mSmsNaranjoOn:String? = null
    var mSmsNaranjoOff:String? = null
    var mSmsVerdeOn:String? = null
    var mSmsVerdeOff:String? = null
    var mStatusRojo:Boolean? = null
    var mStatusNaranjo:Boolean? = null
    var mStatusVerde:Boolean? = null

    constructor(){}

    constructor(
        mName: String?,
        mPhone: Int?,
        mSmsRojoOn: String?,
        mSmsRojoOff: String?,
        mSmsNaranjoOn: String?,
        mSmsNaranjoOff: String?,
        mSmsVerdeOn: String?,
        mSmsVerdeOff: String?,
        mStatusRojo:Boolean?,
        mStatusNaranjo:Boolean?,
        mStatusVerde:Boolean?
    ) {
        this.mName = mName
        this.mPhone = mPhone
        this.mSmsRojoOn = mSmsRojoOn
        this.mSmsRojoOff = mSmsRojoOff
        this.mSmsNaranjoOn = mSmsNaranjoOn
        this.mSmsNaranjoOff = mSmsNaranjoOff
        this.mSmsVerdeOn = mSmsVerdeOn
        this.mSmsVerdeOff = mSmsVerdeOff
        this.mStatusRojo = mStatusRojo
        this.mStatusNaranjo = mStatusNaranjo
        this.mStatusVerde = mStatusVerde
    }
}
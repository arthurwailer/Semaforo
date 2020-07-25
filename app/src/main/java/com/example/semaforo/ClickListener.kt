package com.example.semaforo

import android.view.View

interface ClickListener {
    fun onClickSemaforo(vista: View, posicion: Int)
    fun onClickEdit(vista: View, posicion: Int)
    fun onClickDelete(vista: View, posicion: Int)
}
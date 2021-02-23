package com.oyando.mycalcapp

import android.app.Service
import android.content.Intent
import android.os.IBinder

class RemoteService : Service() {


    private val binder = object : CalcService.Stub() {
        override fun basicTypes(
            anInt: Int,
            aLong: Long,
            aBoolean: Boolean,
            aFloat: Float,
            aDouble: Double,
            aString: String?
        ) {

        }

        override fun add(a: Int, b: Int): Int = a.plus(b)

        override fun divide(a: Int, b: Int): Double = a.div(b.toDouble())

        override fun subtract(a: Int, b: Int): Int = a.minus(b)

        override fun multiply(a: Int, b: Int): Int = a.times(b)
    }

    override fun onBind(intent: Intent): IBinder = binder
}
package com.oyando.mycalcapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.text.Editable
import androidx.appcompat.app.AppCompatActivity
import com.oyando.mycalcapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var calcService: CalcService? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            calcService = CalcService.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            calcService = null
        }
    }

    private lateinit var binding: ActivityMainBinding
    override fun onStart() {
        super.onStart()

        val intent = Intent(this, RemoteService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /**
         * ADDITION
         */
        binding.addBtn.setOnClickListener {
            if (inputIsValid()) {
                val a: Int = binding.value1.text.toString().toInt()
                val b: Int = binding.value2.text.toString().toInt()
                val sum = calcService?.add(a, b)
                showResult(sum.toString())
            }

        }


        /**
         * DIVISION
         */
        binding.divideBtn.setOnClickListener {
            if (inputIsValid()) {
                val a: Int = binding.value1.text.toString().toInt()
                val b: Int = binding.value2.text.toString().toInt()
                val product = calcService?.divide(a, b)
                showResult(product.toString())
            }

        }

        /**
         * SUBTRACTION
         */
        binding.subtractBtn.setOnClickListener {
            if (inputIsValid()) {
                val a: Int = binding.value1.text.toString().toInt()
                val b: Int = binding.value2.text.toString().toInt()
                val difference = calcService?.subtract(a, b)
                showResult(difference.toString())
            }

        }

        /**
         * MULTIPLICATION
         */
        binding.multiplyBtn.setOnClickListener {
            if (inputIsValid()) {
                val a: Int = binding.value1.text.toString().toInt()
                val b: Int = binding.value2.text.toString().toInt()
                val product = calcService?.multiply(a, b)
                showResult(product.toString())
            }

        }

    }

    /**
     * SHOW RESULT
     */
    private fun showResult(value: String) {

        binding.result.text = Editable.Factory.getInstance().newEditable(value)

    }

    /**
     * INPUT VALIDATION
     */
    private fun inputIsValid(): Boolean {
        if (binding.value1.text.isNullOrEmpty()) {
            binding.value1.error = "Enter Input"
            return false
        }

        if (binding.value2.text.isNullOrEmpty()) {
            binding.value2.error = "Enter Input"
            return false
        }
        return true
    }
}
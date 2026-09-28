package com.example.dsam_act03_android_studio_inicial

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tvHeight: TextView
    private var isMale : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tvHeight = findViewById<TextView>(R.id.tvHeight)
        val btnMale = findViewById<TextView>(R.id.btnMale)
        btnMale.setOnClickListener(setSex(true))
        val btnFemale = findViewById<TextView>(R.id.btnFemale)
        btnFemale.setOnClickListener(setSex(false))
    }

    fun setSex(var estat :Boolean ){
        isMale = estat
    }
}
package com.example.dsam_act03_android_studio_inicial

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import kotlin.math.pow
import kotlin.math.roundToLong
class MainActivity : AppCompatActivity() {
    private var height: Int = 0
    private lateinit var heightTV : TextView
    private lateinit var ageTV : TextView
    private lateinit var weightTV : TextView
    private var isMale : Boolean = true
    private  lateinit var btnMale : CardView



    private lateinit var btnFemale : CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        btnMale = findViewById<CardView>(R.id.cardMale)

        btnFemale = findViewById<CardView>(R.id.cardFemale)
        btnMale.setOnClickListener { isMale = true
            updateGenderSelection()}
        btnFemale.setOnClickListener { isMale = false
            updateGenderSelection()}
        heightTV = findViewById<TextView>(R.id.tvHeightValue)

        ageTV = findViewById<TextView>(R.id.tvAgeValue)

        weightTV = findViewById<TextView>(R.id.tvWeightValue)

        ageTV.text = "18"
        weightTV.text = "170"
        val seekBar = findViewById<SeekBar>(R.id.seekBarHeight)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                height = progress
                heightTV.text = "$progress"
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })

        val btnMinusW = findViewById<MaterialButton>(R.id.btnMinusWeight)
        val btnPlusW = findViewById<MaterialButton>(R.id.btnPlusWeight)

        val btnMinusA = findViewById<MaterialButton>(R.id.btnMinusAge)

        val btnPlusA = findViewById<MaterialButton>(R.id.btnPlusAge)

        btnMinusW.setOnClickListener{
            weightTV.text = (Integer.parseInt(weightTV.text.toString())-1).toString()
        }
        btnPlusW.setOnClickListener{
            weightTV.text = (Integer.parseInt(weightTV.text.toString())+1).toString()
        }
        btnMinusA.setOnClickListener{
            ageTV.text = (Integer.parseInt(ageTV.text.toString())-1).toString()
        }
        btnPlusA.setOnClickListener{
            ageTV.text = (Integer.parseInt(ageTV.text.toString())+1).toString()
        }

        val btnCalculate = findViewById<CardView>(R.id.cardCalculate)

        btnCalculate.setOnClickListener {

            val resultadoBMI = calcularBMI(isMale,Integer.parseInt(ageTV.text.toString()) , height, Integer.parseInt(weightTV.text.toString()))
            val rangoBMI = evaluarRangoBMI(resultadoBMI)

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("EXTRA_BMI_RESULT", resultadoBMI)
                putExtra("EXTRA_BMI_RANGE", rangoBMI)
            }

            startActivity(intent)
        }

    }

    private fun updateGenderSelection() {
        val colorSelected = ContextCompat.getColor(this, R.color.card_background_selected)
        val colorUnselected = ContextCompat.getColor(this, R.color.cards)

        if (isMale) {
            btnMale.setCardBackgroundColor(colorSelected)
            btnFemale.setCardBackgroundColor(colorUnselected)
        } else {
            btnFemale.setCardBackgroundColor(colorSelected)
            btnMale.setCardBackgroundColor(colorUnselected)
        }


    }

    fun calcularBMI(isMale: Boolean, edad: Int, alturaInches: Int, pesoLbs: Int): Double {
        if (alturaInches <= 0 || pesoLbs <= 0) {
            return 0.0
        }

        val bmi = (pesoLbs.toDouble() / alturaInches.toDouble().pow(2.0)) * 703.0
        return (bmi * 10.0).roundToLong() / 10.0
    }
    fun evaluarRangoBMI(bmi: Double): Int {
        return when {
            bmi < 18.5 -> -1
            bmi in 18.5..24.9 -> 0
            else -> 1
        }
    }

}
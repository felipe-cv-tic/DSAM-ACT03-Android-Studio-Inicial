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
class SecondActivity : AppCompatActivity() {
    private var bmiResult: Double = 0.0
    private var bmiRange: Int = 0
    private lateinit var bmiText : TextView
    private lateinit var bmiNum : TextView
    private lateinit var bmiMiniText : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.second_activity)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainSecond)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        bmiResult = intent.getDoubleExtra("EXTRA_BMI_RESULT", 0.0)
        bmiRange = intent.getIntExtra("EXTRA_BMI_RANGE",0)

        bmiText = findViewById<TextView>(R.id.bmiText)
        bmiNum = findViewById<TextView>(R.id.bmiNum)
        bmiMiniText = findViewById<TextView>(R.id.bmiMiniText)
        bmiNum.text = bmiResult.toString()
        var rngBmiString = rangeBMI(bmiRange)
        when (rngBmiString) {
            "PESO BAJO" -> {
                bmiText.text = "PESO BAJO"
                bmiMiniText.text = "PESO BAJO"
                val color = ContextCompat.getColor(this, R.color.bajopeso)
                bmiText.setTextColor(color)

            }
            "NORMAL" ->{
                bmiText.text = "NORMAL"
                bmiMiniText.text = "NORMAL"
                val color = ContextCompat.getColor(this, R.color.normal)
                bmiText.setTextColor(color)

            }
            "SOBREPESO" ->{
                bmiText.text = "SOBREPESO"
                bmiMiniText.text = "SOBREPESO"
                val color = ContextCompat.getColor(this, R.color.sobrepeso)
                bmiText.setTextColor(color)

            }
        }

        val btnBack = findViewById<Button>(R.id.back)

        btnBack.setOnClickListener {

            finish()
        }

        val btnReCalc = findViewById<CardView>(R.id.cardReCalculate)

        btnReCalc.setOnClickListener {

            finish()
        }

    }

    private fun rangeBMI (value:Int) : String{
        when (value) {
            -1 -> return "PESO BAJO"
            0 -> return "NORMAL"
            else -> return "SOBREPESO"
        }
    }
}
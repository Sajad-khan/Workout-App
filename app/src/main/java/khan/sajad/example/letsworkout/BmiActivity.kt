package khan.sajad.example.letsworkout

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import khan.sajad.example.letsworkout.databinding.ActivityBmiBinding
import kotlin.math.pow

class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiBinding
    private var inputWeight: Double? = null
    private var inputHeight: Double? = null
    private var inputHeightFeet: Double? = null
    private var inputHeightInches: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.bmiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.bmiToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.metirc_radio_button -> setMetricView()
                else -> setUsView()
            }
        }

        binding.btnCalculate.setOnClickListener {
            when(binding.radioGroup.checkedRadioButtonId){
                R.id.metirc_radio_button -> {
                    inputWeight = binding.inputWeight.text.toString().toDoubleOrNull()
                    inputHeight = binding.inputHeight.text.toString().toDoubleOrNull()
                    if(inputWeight==null || inputHeight==null || inputHeight == 0.0){
                        Toast.makeText(this@BmiActivity, "Please enter valid values!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val bmi = calculateBmi()
                        setBmiScreen(bmi)
                    }
                }

                else -> {
                    inputWeight = binding.inputWeight.text.toString().toDoubleOrNull()
                    inputHeightFeet = binding.inputHeightFeet.text.toString().toDoubleOrNull()
                    inputHeightInches = binding.inputHeightInches.text.toString().toDoubleOrNull()

                    if(inputWeight==null || inputHeightFeet==null||inputHeightInches==null
                        ||inputHeightFeet==0.0){
                        Toast.makeText(this@BmiActivity, "Please enter valid values!", Toast.LENGTH_SHORT).show()
                    }
                    else{
                        inputHeight = 2.54*(12* inputHeightFeet!! + inputHeightInches!!)
                        val bmi = calculateBmi()
                        setBmiScreen(bmi)
                    }
                }
            }

        }
    }

    private fun setUsView() {
        binding.inputLayoutHeight.visibility = View.INVISIBLE
        binding.inputHeightUs.visibility= View.VISIBLE
    }

    private fun setMetricView() {
        binding.inputLayoutHeight.visibility = View.VISIBLE
        binding.inputHeightUs.visibility= View.GONE
    }

    private fun calculateBmi(): Double{
        var bmi = 0.0
        when {
            inputWeight != null && inputHeight !=null -> {
                val heightMetres = inputHeight!! /100
                bmi = (inputWeight)?.div(heightMetres.pow(2.0))!!
            }
        }
        return bmi
    }

    private fun setBmiScreen(bmi: Double){
        binding.tvBmi.visibility = View.VISIBLE
        binding.bmiValue.visibility = View.VISIBLE
        val bmi = String.format("%.2f", bmi).toDouble()
        binding.bmiValue.text = getString(R.string.bmi_value_string, bmi.toString())
        if(bmi > 25.0) binding.tvBmi.text = getString(R.string.overweight)
        else if(bmi < 18.0) binding.tvBmi.text = getString(R.string.underweight)
        else binding.tvBmi.text = getString(R.string.normal)
    }
}




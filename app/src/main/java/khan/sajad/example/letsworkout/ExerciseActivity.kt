package khan.sajad.example.letsworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import khan.sajad.example.letsworkout.data.Exercises
import khan.sajad.example.letsworkout.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExerciseBinding
    private var position = 0
    private var exerciseList = Exercises().loadExercises()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.exerciseToolbar)
        // enables back button on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // clicklistener to the back button on the toolbar
        binding.exerciseToolbar.setNavigationOnClickListener {
            position = 0
            onBackPressed()
        }
        startTimer()
    }

    private fun startTimer(){
        object : CountDownTimer(10000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = (millisUntilFinished / 1000).toString()
                binding.progressBar.progress = ((millisUntilFinished)/1000).toInt()
            }

            override fun onFinish() {

                setExerciseVisibility()
                startTimer30()
            }
        }.start()
    }

    private fun startTimer30(){
            val exercise = exerciseList[position]
            binding.tvTitle30.text = exercise.name
            showGif(exercise.image)
            object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer30.text = (millisUntilFinished / 1000).toString()
                binding.progressBar30.progress = ((millisUntilFinished)/1000).toInt()
            }

            override fun onFinish() {
                position++
                if(position != exerciseList.size){
                    setBreakVisibility()
                    startTimer()
                }
                else{
                    setResultActivity()
                }
            }
        }.start()
    }

    private fun showGif(id: Int) {
        Glide.with(this).load(id).into(binding.ImageView30)
    }
    private fun setExerciseVisibility(){
        binding.frameLayout.visibility = View.GONE
        binding.tvTitle.visibility = View.GONE
        binding.frameLayout30.visibility = View.VISIBLE
        binding.tvTitle30.visibility = View.VISIBLE
        binding.ImageView30.visibility = View.VISIBLE
    }

    private fun setBreakVisibility(){
        binding.frameLayout30.visibility = View.GONE
        binding.tvTitle30.visibility = View.GONE
        binding.ImageView30.visibility = View.GONE
        binding.frameLayout.visibility = View.VISIBLE
        binding.tvTitle.visibility = View.VISIBLE
    }
    private fun setResultActivity(){
        val intent = Intent(this, ResultActivity::class.java)
        startActivity(intent)
        finish()
    }
}
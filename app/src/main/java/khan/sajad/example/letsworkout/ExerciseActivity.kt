package khan.sajad.example.letsworkout

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import khan.sajad.example.letsworkout.data.Exercises
import khan.sajad.example.letsworkout.databinding.ActivityExerciseBinding
import java.util.*

class ExerciseActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var binding: ActivityExerciseBinding
    private var position = 0
    private var exerciseList = Exercises().loadExercises()
    private lateinit var timer: CountDownTimer
    // Text To speech variable
    private lateinit var textToSpeech: TextToSpeech

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.exerciseToolbar)
        // enables back button on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        // click-listener to the back button on the toolbar
        binding.exerciseToolbar.setNavigationOnClickListener {
            position = 0
            onBackPressed()
        }
        // start textToSpeech
        textToSpeech = TextToSpeech(this, this)
    }

    private fun startTimer(){
        binding.tvTitle.text = getString(R.string.get_ready, exerciseList[position].name)

        // Speak out
        textToSpeech.speak(binding.tvTitle.text, TextToSpeech.QUEUE_FLUSH, null, "")

        timer = object : CountDownTimer(10000, 1000) {
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
            textToSpeech.speak(binding.tvTitle30.text, TextToSpeech.QUEUE_FLUSH, null, "")
            Log.e("SPEAK", "Unable to speak!")
            timer = object : CountDownTimer(30000, 1000) {
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


    // OnIt for text to speech
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val langSet = textToSpeech.setLanguage(Locale.ENGLISH)
            if(langSet == TextToSpeech.LANG_MISSING_DATA || langSet == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("TTS", "Unable set the language!")
            }
            else startTimer()
        }
        else{
            Log.e("TTS", "Failed to start Text to speech")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        textToSpeech.stop()
        textToSpeech.shutdown()
    }

    override fun onBackPressed() {
        super.onBackPressed()
//        val intent = Intent(this, MainActivity::class.java)
//        startActivity(intent)
//        finish()
        textToSpeech.stop()
        textToSpeech.shutdown()
        timer.cancel()
        position = 0
    }
}
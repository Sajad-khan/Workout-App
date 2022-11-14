package khan.sajad.example.letsworkout

import android.content.Intent
import android.icu.util.Calendar
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import khan.sajad.example.letsworkout.database.HistoryDao
import khan.sajad.example.letsworkout.database.HistoryEntity
import khan.sajad.example.letsworkout.databinding.ActivityResultBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRestart.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val dao = (application as WorkoutApp).database.historyDao()
        addDateToHistory(dao)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun addDateToHistory(historyDao: HistoryDao){
        val calendar = Calendar.getInstance()
        val time = calendar.time
        Log.e("Date : ", ""+time)
        val formattedTime = SimpleDateFormat("dd MM yyyy HH:mm:ss", Locale.getDefault())
        val date = formattedTime.format(time)
        Log.e("Formatted Date : ", ""+date)

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity(date))
        }
    }
}
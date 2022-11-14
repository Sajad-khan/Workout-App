package khan.sajad.example.letsworkout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import khan.sajad.example.letsworkout.adapter.ItemAdapter
import khan.sajad.example.letsworkout.database.HistoryDao
import khan.sajad.example.letsworkout.databinding.ActivityHistoryBinding
import kotlinx.coroutines.launch

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.historyToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.historyToolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
        val dao = (application as WorkoutApp).database.historyDao()
        getCompletedExercises(dao)
    }
    private fun getCompletedExercises(historyDao: HistoryDao){
        lifecycleScope.launch {
            historyDao.fetchAllDates().collect{allDates ->
                if(allDates.isNotEmpty()){
                    binding.tvHistory.visibility = View.VISIBLE
                    binding.rvHistory.visibility = View.VISIBLE
                    binding.tvNoDataAvailable.visibility = View.INVISIBLE
                    binding.rvHistory.layoutManager = LinearLayoutManager(this@HistoryActivity)
                    val dates = ArrayList<String>()
                    for(it in allDates){
                        dates.add(it.date)
                    }
                    val historyAdapter = ItemAdapter(dates)
                    binding.rvHistory.adapter = historyAdapter
                }
                else{
                    binding.tvHistory.visibility = View.INVISIBLE
                    binding.rvHistory.visibility = View.INVISIBLE
                    binding.tvNoDataAvailable.visibility = View.VISIBLE
                }
            }
        }
    }
}
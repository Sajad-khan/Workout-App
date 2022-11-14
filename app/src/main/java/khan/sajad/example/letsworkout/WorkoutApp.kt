package khan.sajad.example.letsworkout

import android.app.Application
import khan.sajad.example.letsworkout.database.HistoryDatabase

class WorkoutApp: Application() {
    val database by lazy {
        HistoryDatabase.getInstance(this)
    }
}
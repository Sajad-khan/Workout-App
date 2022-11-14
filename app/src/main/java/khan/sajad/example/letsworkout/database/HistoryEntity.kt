package khan.sajad.example.letsworkout.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history_table")
data class HistoryEntity(
    @PrimaryKey
    val date: String
)

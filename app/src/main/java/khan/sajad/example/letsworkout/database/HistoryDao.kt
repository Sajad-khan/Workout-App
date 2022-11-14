package khan.sajad.example.letsworkout.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {
    @Insert
    suspend fun insert(historyEntity: HistoryEntity)

    @Query("SELECT * FROM `history_table`")
    fun fetchAllDates(): kotlinx.coroutines.flow.Flow<List<HistoryEntity>>
}
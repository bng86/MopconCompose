package tw.andyang.mopconcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase
import tw.andyang.mopconcompose.model.Todo

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun todoDao(): TodoDao
}
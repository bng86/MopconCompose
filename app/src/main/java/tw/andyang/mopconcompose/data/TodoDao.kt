package tw.andyang.mopconcompose.data

import androidx.lifecycle.LiveData
import androidx.room.*
import tw.andyang.mopconcompose.model.Todo

@Dao
interface TodoDao {
    @Query("SELECT * FROM todos ORDER BY id DESC")
    fun queryAll(): LiveData<List<Todo>>

    @Insert
    suspend fun insertAll(vararg todo: Todo)

    @Update
    suspend fun update(todo: Todo)

    @Query("SELECT * FROM todos WHERE id = :id")
    fun findById(id: Int): LiveData<Todo>

    @Delete
    suspend fun deleteTodo(todo: Todo)
}
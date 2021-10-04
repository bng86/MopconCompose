package tw.andyang.mopconcompose.data

import android.content.Context
import androidx.room.Room
import tw.andyang.mopconcompose.model.Todo

class AppRepository(context: Context) {

    private val db =
        Room.databaseBuilder(context, AppDatabase::class.java, "mopcon_compose").build()

    fun queryTodos() = db.todoDao().queryAll()

    suspend fun insertTodo(todo: Todo) {
        db.todoDao().insertAll(todo)
    }

    suspend fun updateTodo(todo: Todo) {
        db.todoDao().update(todo)
    }

    fun findById(id: Int) = db.todoDao().findById(id)

    suspend fun deleteTodo(data: Todo) = db.todoDao().deleteTodo(data)
}
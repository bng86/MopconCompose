package tw.andyang.mopconcompose.ui.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import tw.andyang.mopconcompose.data.AppRepository
import tw.andyang.mopconcompose.model.Todo

class TodoViewModel(
    private val repo: AppRepository
) : ViewModel() {
    fun fetchTodo(): LiveData<List<Todo>> {
        return repo.queryTodos()
    }
    fun addTodo(text: String) {
        viewModelScope.launch { repo.insertTodo(Todo(false, text)) }
    }
    fun findById(id: Int): LiveData<Todo> {
        return repo.findById(id)
    }
    fun editTodo(todo: Todo) {
        viewModelScope.launch { repo.updateTodo(todo) }
    }
    fun deleteTodo(data: Todo) {
        viewModelScope.launch { repo.deleteTodo(data) }
    }
}
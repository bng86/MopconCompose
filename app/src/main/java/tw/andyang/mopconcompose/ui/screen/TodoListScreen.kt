package tw.andyang.mopconcompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import tw.andyang.mopconcompose.Route
import tw.andyang.mopconcompose.model.Todo
import tw.andyang.mopconcompose.ui.view.TodoItem

@Composable
fun TodoListScreen(navController: NavHostController) {
    val vm = getViewModel<TodoViewModel>()

    val todos = vm.fetchTodo().observeAsState(initial = emptyList())
    TodoListContent(
        todos = todos.value,
        onCheckedChange = { todo ->
            vm.editTodo(todo)
        },
        onAdd = {
            navController.navigate(Route.CREATE_TODO)
        },
        onEdit = { todo ->
            navController.navigate("edit_todo/${todo.id}")
        }
    )
}

@Composable
fun TodoListContent(
    todos: List<Todo>, onCheckedChange: (Todo) -> Unit = { },
    onAdd: () -> Unit = {}, onEdit: (Todo) -> Unit = {},
) {
    val todoList = todos.filter { !it.checked }
    val doneList = todos.filter { it.checked }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F3437)),
    ) {
        LazyColumn {
            item {
                Text(
                    modifier = Modifier.padding(8.dp),
                    text = "My Todo List",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            if (todoList.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Todo",
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                }
                items(todoList, key = { it.id }) { todo ->
                    TodoItem(
                        todo = todo,
                        onCheckedChange = onCheckedChange,
                        onItemClick = onEdit
                    )
                }
            }
            if (doneList.isNotEmpty()) {
                item {
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text = "Done",
                        color = Color.White,
                        fontSize = 16.sp,
                    )
                }
                items(doneList, key = { it.id }) { todo ->
                    TodoItem(todo = todo, onCheckedChange = onCheckedChange, onItemClick = onEdit)
                }
            }
        }
        Button(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            onClick = onAdd,
        ) { Text(text = "add") }
    }
}

@Preview
@Composable
fun TodoListContentPreview() {
    TodoListContent(
        listOf(
            Todo(true, "TODO"),
            Todo(false, "TODO"),
        )
    )
}
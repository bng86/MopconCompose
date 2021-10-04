package tw.andyang.mopconcompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import tw.andyang.mopconcompose.model.Todo

@Composable
fun EditTodoScreen(navController: NavHostController, id: Int) {
    val vm = getViewModel<TodoViewModel>()
    val todo = vm.findById(id).observeAsState().value ?: return
    EditTodoContent(
        todo = todo,
        onEdit = { data ->
            vm.editTodo(data)
            navController.popBackStack()
        },
        onDelete = { data ->
            vm.deleteTodo(data)
            navController.popBackStack()
        }
    )
}

@Composable
fun EditTodoContent(
    todo: Todo,
    onEdit: (Todo) -> Unit = {},
    onDelete: (Todo) -> Unit = {},
) {
    var value: String by remember { mutableStateOf(todo.text) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2F3437))
            .padding(8.dp),
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = { text ->
                value = text
            },
            colors = TextFieldDefaults.textFieldColors(textColor = Color.White)
        )
        Row {
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    onEdit.invoke(todo.copy(text = value))
                }
            ) {
                Text(text = "Update")
            }
            Button(
                modifier = Modifier.padding(8.dp),
                onClick = {
                    onDelete.invoke(todo)
                }
            ) {
                Text(text = "Delete")
            }
        }
    }
}

@Preview
@Composable
fun EditTodoContentPreview() {
    EditTodoContent(Todo(true, "TODO"))
}
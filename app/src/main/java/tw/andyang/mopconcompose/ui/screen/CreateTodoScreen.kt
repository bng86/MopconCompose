package tw.andyang.mopconcompose.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateTodoScreen(navController: NavHostController) {
    val vm = getViewModel<TodoViewModel>()
    CreateTodoContent(
        onCreate = { text ->
            vm.addTodo(text)
            navController.popBackStack()
        }
    )
}

@Composable
fun CreateTodoContent(
    onCreate: (String) -> Unit = {},
) {
    var value: String by remember { mutableStateOf("") }
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
        Button(
            modifier = Modifier.padding(8.dp),
            onClick = {
                onCreate.invoke(value)
            }
        ) {
            Text(text = "Submit")
        }
    }
}

@Preview
@Composable
fun CreateTodoContentPreview() {
    CreateTodoContent()
}
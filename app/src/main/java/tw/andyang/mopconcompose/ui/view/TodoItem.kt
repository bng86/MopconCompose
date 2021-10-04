package tw.andyang.mopconcompose.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import tw.andyang.mopconcompose.model.Todo

@Composable
fun TodoItem(
    todo: Todo,
    onCheckedChange: (Todo) -> Unit = { },
    onItemClick: (Todo) -> Unit = { },
) {
    Row(
        modifier = Modifier
            .clickable { onItemClick.invoke(todo) }
            .background(Color(0xFF2F3437))
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Checkbox(
            checked = todo.checked,
            onCheckedChange = { checked -> onCheckedChange(todo.copy(checked = checked)) },
            colors = CheckboxDefaults.colors(uncheckedColor = Color.White)
        )
        Text(
            modifier = Modifier
                .clickable { onCheckedChange.invoke(todo.copy(checked = !todo.checked)) }
                .align(Alignment.CenterVertically)
                .padding(start = 8.dp),
            text = todo.text,
            color = if (todo.checked) Color.LightGray else Color.White,
            style = TextStyle(
                textDecoration = TextDecoration.combine(
                    arrayListOf<TextDecoration>().apply {
                        if (todo.checked) {
                            add(TextDecoration.LineThrough)
                        }
                    }
                )
            )
        )
    }
}

@Preview
@Composable
fun TodoItemPreview() {
    TodoItem(
        Todo(false, "TODO")
    )
}

@Preview
@Composable
fun TodoItemCheckedPreview() {
    TodoItem(
        Todo(true, "TODO")
    )
}

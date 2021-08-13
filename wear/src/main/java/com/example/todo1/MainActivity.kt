package com.example.todo1



import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.foundation.ArcPaddingValues
import androidx.wear.compose.foundation.BasicCurvedText
import androidx.wear.compose.foundation.CurvedRow
import androidx.wear.compose.material.ListHeader
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import com.example.todo1.theme.Todo1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TodoListViewModel = viewModel()
            Todo1Theme {
                TodoListScreen(
                    todoList = viewModel.todoList,
                    onTodoToggled = { viewModel.onTodoToggled(it) })
            }
        }
    }
}

class TodoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Text(text = "Hello world.")
            }
        }
    }
}


data class Todo(var text: String, var checked: Boolean = false)

val myTodoList = listOf(
    Todo("Espaço"),
    Todo("Espaço"),
    Todo("Café - Ferver água"),
    Todo("Comer"),
    Todo("Remédio"),
    Todo("Café - Desligar água"),
    Todo("Escovar os dentes"),
    Todo("Pegar toalha"),
    Todo("Separar roupas"),
    Todo("Sabonete rosto"),
    Todo("Óleo"),
    Todo("Vestir"),
    Todo("Estender toalha"),
    Todo("Espaço"),
    Todo("Espaço"),
)


@Composable
fun TodoListScreen(todoList: List<Todo>, onTodoToggled: (Todo) -> Unit) {
    Surface {
        TodoList(todoList, onTodoToggled)
    }
}

@Composable
fun TodoList(todoList: List<Todo>, onTodoToggled: (Todo) -> Unit) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {

        item { Spacer(modifier = Modifier.size(20.dp)) }
        item { ListHeader { Text("Header1") } }

        items(todoList) { todo ->
            TodoListItem(todo, onTodoToggled)
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, onTodoToggled: (Todo) -> Unit) {
    Box {
        SplitToggleChip(
            checked = todo.checked,
            label = { Text(todo.text) },
            secondaryLabel = { Text(todo.text) },
            onCheckedChange = { onTodoToggled(todo) },
            onClick = { },
        )
    }
}


@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun DefaultPreview() {
    Todo1Theme {
        TodoListScreen(myTodoList) { }
    }
}


@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun CurvedRowTest() {

    CurvedRow(modifier = Modifier.fillMaxSize()) {
        BasicCurvedText(
            "Curved Texts",
            fontSize = 16.sp,
            color = Color.Black,
            background = Color.White,
            contentArcPadding = ArcPaddingValues(2.dp)
        )
        Box(
            modifier = Modifier
                .size(20.dp)
                .background(Color.Gray)
        )
        BasicText(
            "Normal Text",
            Modifier
                .background(Color.White)
                .padding(2.dp),
            TextStyle(
                color = Color.Black,
                fontSize = 16.sp,
            )
        )
    }
}
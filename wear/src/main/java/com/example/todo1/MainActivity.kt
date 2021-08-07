package com.example.todo1

//import androidx.wear.compose.material.Surfa
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import com.example.todo1.theme.Todo1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Todo1Theme {
                TodoListScreen()
            }
        }
    }
}


data class Todo(var text: String, var checked: Boolean = false)

class TodoListViewModel : ViewModel() {

    private val myTodoList = (1..8).map { n -> Todo("Do this $n") }.toMutableList()

    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _todoList = MutableLiveData<MutableList<Todo>>(myTodoList)
//
//    private val users: MutableLiveData<MutableList<User>> by lazy {
//        MutableLiveData<List<User>>().also {
//            loadUsers()
//        }
//    }


    val todoList: LiveData<MutableList<Todo>> = _todoList

    // onNameChange is an event we're defining that the UI can invoke
    // (events flow up from UI)
//    fun onTodoListChange(newTodoLis: List<Todo>) {
//        _todoList.value = newTodoLis
//    }
    fun onTodoToggle(todo: Todo) {
        todo.checked = !todo.checked
    }
}


@Composable
fun TodoListItem(todo: Todo) {
    SplitToggleChip(
        checked = todo.checked,
        label = { Text(todo.text) },
        secondaryLabel = { Text(todo.text) },
        onCheckedChange = {},
        onClick = {},
    )
}

@Composable
fun TodoList(todoList: List<Todo>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),

        ) {
        items(todoList) { todo ->
            TodoListItem(todo)
        }
    }
}

val myTodoList = listOf<Todo>(
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
//fun TodoListScreen(todoListViewModel: TodoListViewModel = viewModel()) {
fun TodoListScreen() {
//    private val viewModel: ProfileViewModel by viewModels()

    Surface {
//        observeAsState
//        val todoList: MutableList<Todo> by todoListViewModel.todoList.observeAsState("")
        TodoList(myTodoList)
    }
//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
////        CurvedRow() {} todo when updaiting
//
//    }
}

@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun DefaultPreview() {
    Todo1Theme {
        TodoListScreen()
    }
}
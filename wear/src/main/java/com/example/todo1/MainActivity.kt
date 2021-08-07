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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import com.example.todo1.theme.Todo1Theme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: TodoListViewModel = viewModel()
//            val todoList by viewModel.todoList.observeAsState(listOf())
//            val todoList = viewModel.tdl

            Todo1Theme {
                TodoListScreen(
                    todoList = viewModel.todoList,
                    onTodoToggled = { viewModel.onTodoToggled(it) })
            }
        }
    }
}


data class Todo(var text: String, var checked: Boolean = false)

class TodoListViewModel : ViewModel() {

    private val initialTodoList = (1..8).map { n -> Todo("Do this $n") }.toTypedArray()
//    private val initialTodoList = (1..8).map { n -> Todo("Do this $n") }.toMutableList()
//    private val _todoList = MutableLiveData(initialTodoList)
//    val todoList: LiveData<List<Todo>> = _todoList

    var todoList = mutableStateListOf(*initialTodoList)


    fun onTodoToggled(todo: Todo) {
        println("Toggling $todo")
        todo.checked = !todo.checked
        val idx = todoList.indexOf(todo)
        todoList[idx] = todo  // trigger list update
    }

    fun removeTodo(toRemove: Todo) {
        println("Removing $toRemove")
    }


//    private val users: MutableLiveData<List<User>> by lazy {
//        MutableLiveData<List<User>>().also {
//            loadUsers()
//        }
//    }
//
//    fun getUsers(): LiveData<List<User>> {
//        return users
//    }
//
//    private fun loadUsers() {
//        // Do an asynchronous operation to fetch users.
//    }

}

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
        items(todoList) { todo ->
            TodoListItem(todo, onTodoToggled)
        }
    }
}

@Composable
fun TodoListItem(todo: Todo, onTodoToggled: (Todo) -> Unit) {
    SplitToggleChip(
        checked = todo.checked,
        label = { Text(todo.text) },
        secondaryLabel = { Text(todo.text) },
        onCheckedChange = { onTodoToggled(todo) },
        onClick = {  },
    )
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


@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun DefaultPreview() {
    Todo1Theme {
        TodoListScreen(myTodoList) { }
    }
}
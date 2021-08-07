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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.SplitToggleChip
import androidx.wear.compose.material.Text
import com.example.todo1.theme.Todo1Theme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val todoListViewModel: TodoListViewModel = viewModel()
            val name: String by todoListViewModel.name.observeAsState("default")
            Todo1Theme {
                TodoListScreen(name = name)
            }
        }
    }
}


data class Todo(var text: String, var checked: Boolean = false)

class TodoListViewModel : ViewModel() {

    private val initialTodoList = (1..8).map { n -> Todo("Do this $n") }.toMutableList()
    private val _todoList = MutableLiveData(initialTodoList)


    val todoList: LiveData<MutableList<Todo>> = _todoList


    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
//    private val _checked = MutableLiveData(false)
//    val checked: LiveData<Boolean> = _checked
//
//    // onNameChange is an event we're defining that the UI can invoke
//    // (events flow up from UI)
//    fun onToggleChecked(newChecked: Boolean) {
//        _checked.value = newChecked
//    }


    // LiveData holds state which is observed by the UI
    // (state flows down from ViewModel)
    private val _name = MutableLiveData("")
    val name: LiveData<String> = _name

    // onNameChange is an event we're defining that the UI can invoke
    // (events flow up from UI)
    fun onNameChange(newName: String) {
        _name.value = newName
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
fun TodoListScreen(name: String) {

//    val name: String by todoListViewModel.name.observeAsState("")

    Surface {
        Text(name)
        TodoList(myTodoList)
    }
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
        TodoListScreen("Some name")
    }
}
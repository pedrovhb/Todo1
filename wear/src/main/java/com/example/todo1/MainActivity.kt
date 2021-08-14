package com.example.todo1


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.foundation.ArcPaddingValues
import androidx.wear.compose.foundation.BasicCurvedText
import androidx.wear.compose.foundation.CurvedRow
import androidx.wear.compose.material.*
import com.example.todo1.theme.Todo1Theme
import kotlinx.coroutines.delay
import java.time.LocalTime


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println(this.getSystemService(VIBRATOR_SERVICE))
        setContent {
            MyApp()
        }
    }
}


data class Todo(var text: String, var checked: Boolean = false)

@Composable
fun MyApp() {
    val navController = rememberNavController()
    val viewModel: TodoListViewModel = viewModel()

    fun goToTodoList(): Unit {
        println("navController.navigate(\"todoList\")")
        navController.navigate("todoList")
    }

    fun goToReversedTodoList(): Unit {
        println("navController.navigate(\"reversedTodoList\")")
        navController.navigate("reversedTodoList")
    }

    Todo1Theme {
        NavHost(navController = navController, startDestination = "todoList") {
            composable("todoList") {
                TodoListScreen(
                    switchList = { goToReversedTodoList() },
                    todoList = viewModel.todoList,
                    onTodoToggled = { viewModel.toggleTodo(it) },
                    onClearAllClicked = { viewModel.clearAll() },
                )
            }
//            composable("reversedTodoList") {
//                ReversedTodoListScreen(
//                    switchList = { goToTodoList() },
//                    todoList = viewModel.todoList,
//                    onTodoToggled = { viewModel.onTodoToggled(it) })
//            }
        }
    }

}


fun getCurrentTimeFormatted(): String {
    val currentTime = LocalTime.now()
    return String.format("%1\$tI:%1\$tM", currentTime)
}

@Composable
fun currentTime(): String {
    var currentTime: String by remember { mutableStateOf(getCurrentTimeFormatted()) }
    LaunchedEffect(key1 = currentTime) {
        delay(1000)
        currentTime = getCurrentTimeFormatted()
    }
    return currentTime
}

@Composable
fun TodoListScreen(
    switchList: () -> Unit,
    todoList: List<Todo>,
    onTodoToggled: (Todo) -> Unit,
    onClearAllClicked: () -> Unit,
) {
    Surface {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(currentTime(), Modifier.padding(4.dp))
//            Button(onClick = switchList) {
//                Text(text = "Switch order")
//            }
            TodoList(
                todoList = todoList,
                onTodoToggled = onTodoToggled,
                onClearAllClicked = onClearAllClicked
            )
        }
    }
}

//@Composable
//fun ReversedTodoListScreen(
//    switchList: () -> Unit,
//    todoList: List<Todo>,
//    onTodoToggled: (Todo) -> Unit
//) {
//    Surface {
//        Column(horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = switchList) {
//                Text("Yo!")
//            }
//            TodoList(
//                todoList = todoList.reversed(),
//                onTodoToggled = onTodoToggled
//            )
//        }
//    }
//}

@Composable
fun TodoList(todoList: List<Todo>, onTodoToggled: (Todo) -> Unit, onClearAllClicked: () -> Unit) {

    val scalingLazyColumnState = rememberScalingLazyColumnState()
    ScalingLazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        state = scalingLazyColumnState
    ) {

        item { Spacer(modifier = Modifier.size(10.dp)) }
        item { ListHeader { Text("Rotina - Manhã") } }
        items(todoList.size) { todoIndex ->
            TodoListItem(todoList[todoIndex], onTodoToggled)
        }
        item {
//            Box(modifier = Modifier.fillMaxSize())
            Button(onClick = onClearAllClicked, Modifier.fillMaxSize()) {
                Text("Clear all")
            }
        }
        item { Spacer(modifier = Modifier.size(15.dp)) }
    }
}

@Composable
fun TodoListItem(todo: Todo, onTodoToggled: (Todo) -> Unit) {
    Box {
        SplitToggleChip(
            checked = todo.checked,
            label = { Text(todo.text) },
//            secondaryLabel = { Text(todo.text) },
            onCheckedChange = { onTodoToggled(todo) },
            onClick = {  },
        )
    }
}


//@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Preview(showBackground = true, widthDp = 200, heightDp = 200)
@Composable
fun DefaultPreview() {
    Todo1Theme {
        TodoListScreen(
            switchList = {},
            todoList = myTodoList,
            onTodoToggled = { },
            onClearAllClicked = {  },
        )
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
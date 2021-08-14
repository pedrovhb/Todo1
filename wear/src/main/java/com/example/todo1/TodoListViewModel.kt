package com.example.todo1

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoListViewModel : ViewModel() {

    //    private val initialTodoList = (1..8).map { n -> Todo("Do this $n") }.toTypedArray()
    private val initialTodoList = myTodoList.toTypedArray()

    var todoList = mutableStateListOf(*initialTodoList)


    fun toggleTodo(todo: Todo) {
        println("Toggling $todo")
        todo.checked = !todo.checked
        todoList[0] = todoList[0]
//        todoList.let {
//            val idx = it.indexOf(todo)
//            it.set(idx, todo)
//        }
//        val idx = todoList.indexOf(todo)
//        todoList[idx] = todo
//        todoList[idx] = todo  // trigger list update
//        todoList
    }

    fun clearAll() {
        println("Clearing all")
        todoList.replaceAll { it.apply { this.checked = false } }
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

val myTodoList = listOf(
    Todo("Café - Ferver água"),
    Todo("Comer"),
    Todo("Remédio"),
    Todo("Café - Desligar água"),
    Todo("Escovar os dentes"),
    Todo("Pegar toalha"),
    Todo("Separar roupas"),
    Todo("Banho"),
    Todo("Estender toalha"),
)

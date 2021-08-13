package com.example.todo1

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TodoListViewModel : ViewModel() {

    //    private val initialTodoList = (1..8).map { n -> Todo("Do this $n") }.toTypedArray()
    private val initialTodoList = myTodoList.toTypedArray()

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
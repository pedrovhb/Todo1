package com.example.todo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.todo1.ui.main.PickListFragment

class PickList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pick_list_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PickListFragment.newInstance())
                .commitNow()
        }
    }
}
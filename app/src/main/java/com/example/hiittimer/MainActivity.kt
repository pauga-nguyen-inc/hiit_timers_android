package com.example.hiittimer

import android.app.Activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun openNewTimer(view: View) {
        val launchNewTimer = Intent(this, NewTimer::class.java)
        startActivityForResult(launchNewTimer, 1)
    }
}

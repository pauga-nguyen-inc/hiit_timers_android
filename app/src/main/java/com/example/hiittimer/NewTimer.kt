package com.example.hiittimer

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_edit_timer_info.*
import kotlinx.android.synthetic.main.activity_new_timer.*
import kotlin.concurrent.timer

class NewTimer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_timer)

    }

    fun cancelCreateTimer(view : View){
        val cancelIntent = Intent()
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun saveNewTimer(view : View){
        var timer = Timer::class.java.newInstance()
        timer.setName(timer_name_form.text.toString())
        timer.setRestTime(rest_time_form.text.toString().toInt())
        timer.setWorkingTime(working_time_edit_form.text.toString().toInt())

        sendTimer(view, timer)

    }

    fun sendTimer(view: View, timer: Timer){
        var intent = Intent()
        intent.putExtra("Timer", timer)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}

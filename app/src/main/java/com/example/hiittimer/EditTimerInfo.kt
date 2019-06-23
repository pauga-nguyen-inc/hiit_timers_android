package com.example.hiittimer

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_edit_timer_info.*
import kotlinx.android.synthetic.main.activity_new_timer.*

class EditTimerInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_timer_info)
        editTimerIntent()
    }

    fun saveEditTimer(view: View){
        var timer = Timer::class.java.newInstance()
        timer.setName(timer_name_edit_form.text.toString())
        timer.setWorkingTime(working_time_edit_form.text.toString().toInt())
        timer.setRestTime(rest_time_edit_form.text.toString().toInt())
        timer.setWorkingTime(set_number_edit_form.text.toString().toInt())
        sendTimer(view, timer)
    }

    fun cancelEditTimer(view: View){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun editTimerIntent(){
        val info = intent.extras
        if (info != null){
            val timer = info.getSerializable("timer") as Timer
            timer_name_edit_form.setText(timer.getName(), TextView.BufferType.EDITABLE)
            working_time_edit_form.setText(timer.getWorkingTime(), TextView.BufferType.EDITABLE)
            rest_time_edit_form.setText(timer.getRestTime(), TextView.BufferType.EDITABLE)
            set_number_edit_form.setText(timer.getSetNumber(), TextView.BufferType.EDITABLE)
        }
    }

    fun sendTimer(view: View, timer: Timer){
        var intent = Intent()
        intent.putExtra("EditTimer", timer)
        finish()
    }
}

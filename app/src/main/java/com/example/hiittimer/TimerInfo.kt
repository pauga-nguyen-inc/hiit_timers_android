package com.example.hiittimer

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_timer_info.*


class TimerInfo : AppCompatActivity() {

    var info = Timer::class.java.newInstance()
    lateinit var timer: Timer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_info)

        handleTimerIntent()
    }

    override fun onResume(){
        super.onResume()
    }
    fun editTimerInfo (view: View){
        val editButton = Intent(this, NewTimer::class.java)
        editButton.putExtra("timer", timer)
        startActivityForResult(editButton,1)
    }

    fun doneTimerInfo (view: View) {
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun handleTimerIntent(){
        val info = intent.extras

        if (info != null){
            timer = info.getSerializable("timer") as Timer
            timer_name_info.text = timer.getName()
            working_time_info.text = timer.getWorkingTime().toString()
            rest_time_info.text = timer.getRestTime().toString()
            set_number_info.text = timer.getSetNumber().toString()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            info = data!!.getSerializableExtra("Timer") as Timer
            newTimer(info)
        }
    }

    fun newTimer(timer: Timer){
        timer_name_info.text = timer.getName()
        working_time_info.text = timer.getWorkingTime().toString()
        rest_time_info.text = timer.getRestTime().toString()
        set_number_info.text = timer.getSetNumber().toString()
    }

}

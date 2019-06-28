package com.example.hiittimer

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_edit_timer_info.*
import kotlinx.android.synthetic.main.activity_new_timer.*
import kotlin.concurrent.timer
import kotlin.math.min

class NewTimer : AppCompatActivity() {
    val arrayOfTimes = Array<Int>(60, {it + 1})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_timer)

        numPicker()
    }

    fun cancelCreateTimer(view : View){
        setResult(Activity.RESULT_CANCELED)
        finish()
    }

    fun saveNewTimer(view : View){
        var timer = Timer::class.java.newInstance()
        val dispWorkTime = findViewById<TextView>(R.id.display_working_time)
        val dispRestTime = findViewById<TextView>(R.id.display_rest_time)
  //      val restTime = Integer.parseInt(dispRestTime.toString())

        timer.setName(timer_name_form.text.toString())
        timer.setSetNumber(set_number_form.text.toString().toInt())
        timer.setWorkingTime(dispWorkTime.text.toString())
        timer.setRestTime(dispRestTime.text.toString())


        sendTimer(view, timer)

    }

    fun sendTimer(view: View, timer: Timer){
        var intent = Intent()
        intent.putExtra("Timer", timer)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    fun numPicker(){
        val wminutePicker = findViewById<NumberPicker>(R.id.set_time_form)
        val wsecondPicker = findViewById<NumberPicker>(R.id.set_time_seconds_form)

        val rminutePicker = findViewById<NumberPicker>(R.id.rest_time_form)
        val rsecondPicker = findViewById<NumberPicker>(R.id.rest_time_seconds_form)

        if (wminutePicker != null){
            wminutePicker.minValue = 0
            wminutePicker.maxValue = 59
            wminutePicker.wrapSelectorWheel = true
            wminutePicker.setOnValueChangedListener( {picker, oldVal, newVal ->
                val text = findViewById<TextView>(R.id.display_working_time)
                text.setText("$newVal:" + "%02d".format(wsecondPicker.value))
            } )
        }

        if (wsecondPicker != null){
            wsecondPicker.minValue = 0
            wsecondPicker.maxValue = 59
            wsecondPicker.wrapSelectorWheel = true
            wsecondPicker.setOnValueChangedListener( {picker, oldVal, newVal ->
                val text = findViewById<TextView>(R.id.display_working_time)
                text.setText("${wminutePicker.value}:" + "%02d".format(newVal))
            } )
        }

        if (rminutePicker != null){
            rminutePicker.minValue = 0
            rminutePicker.maxValue = 59
            rminutePicker.wrapSelectorWheel = true
            rminutePicker.setOnValueChangedListener( {picker, oldVal, newVal ->
                val text = findViewById<TextView>(R.id.display_rest_time)
                text.setText("$newVal:" + "%02d".format(rsecondPicker.value))
            } )
        }

        if (rsecondPicker != null){
            rsecondPicker.minValue = 0
            rsecondPicker.maxValue = 59
            rsecondPicker.wrapSelectorWheel = true
            rsecondPicker.setOnValueChangedListener( {picker, oldVal, newVal ->
                val text = findViewById<TextView>(R.id.display_rest_time)
                text.setText("${rminutePicker.value}:" + "%02d".format(newVal))
            } )
        }



    }
}

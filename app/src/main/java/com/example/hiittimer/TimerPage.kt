package com.example.hiittimer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_timer_info.*
import kotlinx.android.synthetic.main.activity_timer_page.*
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import kotlin.math.min

class TimerPage : AppCompatActivity() {
    var info = Timer::class.java.newInstance()
    lateinit var timer: Timer

    private var isPaused = false
    private var isCancelled = false
    private var resumeFromMillis:Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer_page)
        //------------------------------------------------------------------


        var workMin  = 0
        var workSec  = 0
        var restMin = 0
        var restSec = 0
        var arr = emptyList<String>()
        var restArr = emptyList<String>()

        val info = intent.extras
        if (info != null) {
            timer = info.getSerializable("timer") as Timer

            arr = timer.getWorkingTime().split(":")
            workMin = Integer.parseInt(arr[0])
            workSec = Integer.parseInt(arr[1])

            restArr = timer.getRestTime().split(":")
            restMin = Integer.parseInt(restArr[0])
            restSec = Integer.parseInt(restArr[1])
        }


        //-------------------------------------------------------------
        val millisInFuture:Long = (workMin.toLong() * 60)*1000 + (workSec * 1000) + 1000
        val millisInFutureRest : Long = (restMin.toLong() * 60)*1000 + (restSec * 1000) + 1000
        val countDownInterval:Long = 1000


        // Count down timer start button
        button_start.setOnClickListener{
            // Start the timer
            timer(millisInFuture,countDownInterval).start()
            it.isEnabled = false
            button_stop.isEnabled = true
            button_pause.isEnabled = true

            isCancelled = false
            isPaused = false

            //Toast.makeText(this, "${millisInFuture}", Toast.LENGTH_SHORT ).show()

        }

        // Count down timer stop/cancel button
        button_stop.setOnClickListener{
            // Start the timer
            isCancelled = true
            isPaused = false

            it.isEnabled = false
            button_start.isEnabled = true
            button_pause.isEnabled = false
        }


        // Count down timer pause button
        button_pause.setOnClickListener{
            isPaused = true
            isCancelled = false

            it.isEnabled = false
            button_start.isEnabled = false
            button_stop.isEnabled = false
            button_resume.isEnabled = true
        }


        // Count down timer resume button
        button_resume.setOnClickListener{
            // Resume the timer
            timer(resumeFromMillis,countDownInterval).start()

            isPaused = false
            isCancelled = false

            it.isEnabled = false
            button_pause.isEnabled = true
            button_start.isEnabled = false
            button_stop.isEnabled = true
        }
    }



    private fun timer(millisInFuture:Long,countDownInterval:Long):CountDownTimer{
         return object: CountDownTimer(millisInFuture,countDownInterval){
            override fun onTick(millisUntilFinished: Long){

                val timeRemaining = "${(millisUntilFinished / 1000 / 60)}:" +
                        "${(millisUntilFinished / 1000 % 60).toString().padStart(2, '0')} "

                var array = timeRemaining.split(":")
                var rsec = Integer.parseInt(array[0])
                var rmin = Integer.parseInt(array[1].trim())

                var i = 0
                if (i <= timer.getSetNumber().toString().toInt()) {
                    if ((rmin.toLong() * 60)*1000 + (rsec * 1000) == 0L){
                        text_view_set_number.text = i.toString()
                        i++
                        timer(millisInFuture, countDownInterval).start()
                    }
                }

                if (isPaused){
                    text_view.text = "${text_view.text}\nPaused"
                    // To ensure start timer from paused time
                    resumeFromMillis = millisUntilFinished
                    cancel()
                }else if (isCancelled){
                    text_view.text = "${text_view.text}\nStopped.(Cancelled)"
                    cancel()
                }else{
                    text_view.text = timeRemaining
                }
            }

            override fun onFinish() {
//                var i = 1
//                while (i < timer.getSetNumber().toString().toInt()){
//                    text_view_set_number.text = i.toString()
//                }

           }
        }

    }
}

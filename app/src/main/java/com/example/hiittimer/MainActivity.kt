package com.example.hiittimer


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.io.File
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    var info = Timer::class.java.newInstance()
    private var timerList: ArrayList<Timer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timerList = getTimerList()
    }

    fun getTimerList(): ArrayList<Timer>{
        return timerList
    }

    override fun onResume() {
        super.onResume()
        populateTimerView()
    }

    fun populateTimerView(){
        val cardView = findViewById<View>(R.id.timer_card)
        (cardView as android.support.v7.widget.CardView).removeAllViews()

        if (timerList.isEmpty()){
            val noTimers = TextView(this)
            noTimers.text="No Timers Yet"
            noTimers.height = 200
            noTimers.gravity = Gravity.CENTER
            noTimers.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT)
            (cardView as android.support.v7.widget.CardView).addView(noTimers)
        }
        else {
            for (timers in timerList!!){
                val view = TextView(this)
                view.text = "${timers.getName()}, ${timers.getWorkingTime()}," +
                        "${timers.getRestTime()}, ${timers.getSetNumber()}"

                view.height = 200
                view.gravity = Gravity.CENTER
                view.layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
                (cardView as android.support.v7.widget.CardView).addView(view)
                view.setOnClickListener({
                    sendTimer(timers)})

            }
        }
    }
    fun openNewTimer(v: View) {
        val intent = Intent(this, NewTimer::class.java)
        startActivityForResult(intent,1)
        v.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == Activity.RESULT_OK){
            info = data!!.getSerializableExtra("Timer") as Timer
            saveTimer(info)
        }
    }

    fun saveTimer(timer : Timer){
        timerList.add(timer)
        saveToFile(timer)
    }


    fun saveToFile(timer: Timer){
        val file = File(filesDir, timer.getName())
        if(!file.exists()){
            val out = file.printWriter()
            out.print("${timer.getName()}, ${timer.getWorkingTime()}, " +
                    "${timer.getRestTime()}, ${timer.getSetNumber()} ")
            out.close()
        }
    }
    fun sendTimer(timer : Timer){
    }

    fun deleteTimer(timer : Timer){

    }
}
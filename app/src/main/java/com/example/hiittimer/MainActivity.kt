package com.example.hiittimer


import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {

    var info = Timer::class.java.newInstance()
    private var timerList: ArrayList<Timer> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        timerList = getTimerList()
    }
    override fun onResume() {
        super.onResume()
        populateTimerView()
    }
    fun getTimerList(): ArrayList<Timer>{
/*
        val files = applicationContext.fileList()
        val timerList: ArrayList<Timer> = ArrayList()
        var timerInfo: List<String>
        for(file in files) {
            val thisFile = File(filesDir,file)
            var sc = Scanner(thisFile)
            if(sc.hasNext()){
                var line = sc.nextLine()
                val timers: Timer = Timer::class.java.newInstance()
                timerInfo = line.split(",")
                if(timerInfo.size == 4){
                    timers.setName(timerInfo[0])
                    timers.setWorkingTime(timerInfo[1].trim().toInt())
                    timers.setRestTime(timerInfo[2].trim().toInt())
                    timers.setSetNumber(timerInfo[3].trim().toInt())
                    timerList.add(timers)
                }
            }
        }
*/
        return timerList
    }


    fun populateTimerView(){


        val mainLinearLayout = findViewById<View>(R.id.linear_layout)
        (mainLinearLayout as LinearLayout).removeAllViews()
        mainLinearLayout.orientation = LinearLayout.VERTICAL

        val cardLinearLayout = LinearLayout(this)
        cardLinearLayout.orientation = LinearLayout.VERTICAL

        val params = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        params.setMargins(16,16,16,16)

        val cardView = CardView(this)
        cardView.radius = 15f
        cardView.setCardBackgroundColor(Color.parseColor("#009688"))
        cardView.setContentPadding(36,36,36,36)
        cardView.layoutParams = params
        cardView.cardElevation = 30f

        if(timerList.isEmpty()){
            val noView = TextView(this)
            noView.text = "No Timers Yet"
            noView.textSize = 24f
            noView.setTextColor(Color.WHITE)
            noView.gravity = Gravity.CENTER
            cardView.addView(noView)
            mainLinearLayout.addView(cardView)
        }
        else {
            val view = TextView(this)

            for (timers in timerList!!) {
                view.text = "${timers.getName()}, \nWorking Time:${timers.getWorkingTime()}," +
                        "\nRest Time:${timers.getRestTime()}, \nSet Number: ${timers.getSetNumber()}"
                view.textSize = 24f
                view.setTextColor(Color.WHITE)
                view.gravity = Gravity.CENTER

                cardLinearLayout.addView(view)
                cardView.addView(cardLinearLayout)
                mainLinearLayout.addView(cardView)

                view.setOnClickListener({
                    sendTimer(timers)
                })

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
        val intent = Intent(this, TimerInfo::class.java)
        intent.putExtra("timer", timer)
        startActivity(intent)
    }

    fun deleteTimer(timer : Timer){

    }
}
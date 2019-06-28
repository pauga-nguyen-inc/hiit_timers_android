package com.example.hiittimer

import java.io.Serializable

class Timer: Serializable{
    private var name: String = ""
    private var workingTime: String = ""
    private var restTime: String = ""
    private var setNumber: Int = 0

    fun setName(name: String) { this.name = name }
    fun getName(): String { return this.name }

    fun setSetNumber(setNumber: Int) { this.setNumber = setNumber }
    fun getSetNumber(): Int { return this.setNumber}

    fun setWorkingTime(workingTime: String) { this.workingTime = workingTime }
    fun getWorkingTime(): String { return this.workingTime }

    fun setRestTime(restTime: String) { this.restTime = restTime }
    fun getRestTime(): String { return this.restTime}


}
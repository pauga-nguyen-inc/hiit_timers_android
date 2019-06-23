package com.example.hiittimer

import java.io.Serializable

class Timer: Serializable{
    private var name: String = ""
    private var workingTime: Int = 0
    private var restTime: Int = 0
    private var setNumber: Int = 0

    fun setName(name: String) { this.name = name }
    fun getName(): String { return this.name }

    fun setWorkingTime(workingTime: Int) { this.workingTime = workingTime }
    fun getWorkingTime(): Int { return this.workingTime }

    fun setRestTime(restTime: Int) { this.restTime = restTime }
    fun getRestTime(): Int { return this.restTime}

    fun setSetNumber(setNumber: Int) { this.setNumber = setNumber }
    fun getSetNumber(): Int { return this.setNumber}
}
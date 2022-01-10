package com.example.waterplant.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

class TimeManager {

    private val pattern = "yyyy-MM-dd";
    private val formater = DateTimeFormatter.ofPattern(pattern);

    companion object{
        val NEVER = 10000000
    }

    fun dateToString(date: LocalDate?): String {
        return date?.format(formater)!!
    }

    fun stringToDate(date: String): LocalDate? {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern))
    }

    fun today(): LocalDate {
        return LocalDate.now()
    }

    fun todayToString(): String {
        return dateToString(today())
    }

    fun diffDays(d1: LocalDate?, d2: LocalDate?): Int {
        return Period.between(d1, d2).days
    }

    fun nextDate(jourJ: String, interval: Int): LocalDate? {
        return stringToDate(jourJ)?.plusDays(interval.toLong())
    }

    fun nextDateToString(jourJ: String, interval: Int): String {
        return dateToString(nextDate(jourJ,interval))
    }

    fun isToday(date: String) : Boolean {
        return diffDays(stringToDate(date), today() ) <= 0
    }
}
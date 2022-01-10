package com.example.waterplant

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.waterplant.databinding.ActivityArrosageBinding
import com.example.waterplant.databinding.ActivityDecalerBinding
import com.example.waterplant.entities.Plant
import com.example.waterplant.utils.TimeManager
import com.example.waterplant.viewmodels.ArrosageViewModel
import java.util.*
import android.content.DialogInterface
import android.graphics.Color
import androidx.core.content.ContextCompat
import java.text.DecimalFormat
import java.time.LocalDate


class DecalerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDecalerBinding // binding
    private val model by lazy { ViewModelProvider(this).get(ArrosageViewModel::class.java) }
    private val TM = TimeManager()
    private val plant by lazy { intent.getSerializableExtra("oneArrosage") as Plant }
    private val type by lazy { intent.getStringExtra("type") }
    val mCalendar = Calendar.getInstance()
    var delay = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDecalerBinding.inflate(layoutInflater) // binding
        setContentView(binding.root) // binding

        val minDay = LocalDate.now().dayOfMonth
        val minMonth = LocalDate.now().monthValue
        val minYear = LocalDate.now().year

        Log.d("TOTO", "minDay $minDay minMonth $minMonth  minYear $minYear")

        binding.demain.setOnClickListener {
            binding.demain.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.demain.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))

            binding.datePicker.setTextColor(ContextCompat.getColor(this, R.color.MainGreen))
            binding.datePicker.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            delay = 1
            binding.save.isEnabled = true
        }

        binding.datePicker.setOnClickListener {
            binding.datePicker.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.datePicker.setBackgroundColor(ContextCompat.getColor(this, R.color.purple_700))

            binding.demain.setTextColor(ContextCompat.getColor(this, R.color.MainGreen))
            binding.demain.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

            calendar()
            binding.save.isEnabled = true
        }

        binding.btnAnnuler.setOnClickListener {
            finish()
        }


        binding.save.setOnClickListener {
            val year = mCalendar.get(Calendar.YEAR)
            val mFormat = DecimalFormat("00")
            val month = mFormat.format(mCalendar.get(Calendar.MONTH) + 1)
            val day = mFormat.format(mCalendar.get(Calendar.DAY_OF_MONTH))
//            Log.d("TOTO", "DANS SAVE : ")
//            Log.d("TOTO", "delay : $delay")
//            Log.d(
//                "TOTO",
//                " date $year-$month-$day"
//            )
            if (delay == -1) {
                delay = TM.diffDays(TM.today(), TM.stringToDate("$year-$month-$day"))
            }
            Log.d("TOTO", "DANS SAVE : ")
            Log.d("TOTO", "delay : $delay")
            decaler(delay)
        }

    }


    fun decaler(delay: Int) {
        Log.d("ZOZO", "avant $plant")

        if (type == "Arrossage classique") {
            plant.lastArosage = TM.nextDateToString(plant.lastArosage, delay)
        } else {
            plant.lastNutriment = TM.nextDateToString(plant.lastNutriment, delay)
        }
        Log.d("ZOZO", "apres $plant")
        model.updatePlant(plant)
        Toast.makeText(this, "Decaler de $delay jour", Toast.LENGTH_SHORT).show()
        finish()
    }

    fun calendar() {
        // Get instance of calendar
        // mCalendar will be set to current/today's date
        delay = -1
        val year = mCalendar.get(Calendar.YEAR)
        val month = mCalendar.get(Calendar.MONTH)
        val day = mCalendar.get(Calendar.DAY_OF_MONTH)
        Log.d("TOTO", "avant $year $month $day")


        // Creating a simple calendar dialog.
        // It was 9 Aug 2021 when this program was developed.
        val mDialog = DatePickerDialog(
            this,
            { _, mYear, mMonth, mDay ->
                mCalendar[Calendar.YEAR] = mYear
                mCalendar[Calendar.MONTH] = mMonth
                mCalendar[Calendar.DAY_OF_MONTH] = mDay
            },
            mCalendar[Calendar.YEAR],
            mCalendar[Calendar.MONTH],
            mCalendar[Calendar.DAY_OF_MONTH]
        )

        // Changing mCalendar date from current to
        // some random MIN day 15/08/2021 15 Aug 2021
        // If we want the same current day to be the MIN day,
        // then mCalendar is already set to today
        // and the below code will be unnecessary
        val tommorrow = TM.nextDate(TM.todayToString(),1) ?: LocalDate.now()
        val minDay = tommorrow.dayOfMonth
        val minMonth = tommorrow.monthValue
        val minYear = tommorrow.year


        mCalendar.set(minYear, minMonth - 1, minDay)
        mDialog.datePicker.minDate = mCalendar.timeInMillis

        // Changing mCalendar date from current to
        // some random MAX day 20/08/2021 20 Aug 2021
        val maxDate = if (type == "Arrossage classique") {
            TM.nextDate(TM.todayToString(), plant.freqArosage - 1) ?: LocalDate.now()
        } else {
            TM.nextDate(TM.todayToString(), plant.freqNutriment - 1) ?: LocalDate.now()
        }

        val maxDay = maxDate.dayOfMonth
        val maxMonth = maxDate.monthValue
        val maxYear = maxDate.year

//        val maxDay = 20
//        val maxMonth = 1
//        val maxYear = 2022
        mCalendar.set(maxYear, maxMonth - 1, maxDay)
        mDialog.datePicker.maxDate = mCalendar.timeInMillis

        // Display the calendar dialog
        mDialog.show()

//        mDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK",
//            DialogInterface.OnClickListener { dialog, which ->
//                Log.d("TOTO", "DANS OK : ")
//
////                val year = mCalendar.get(Calendar.YEAR)
////                val mFormat = DecimalFormat("00")
////                val month = mFormat.format(mCalendar.get(Calendar.MONTH))
////                val day = mFormat.format(Calendar.DAY_OF_MONTH)
////                Log.d(
////                    "TOTO",
////                    " date $year-$month-$day"
////                )
////
//
////                delay = TM.diffDays(TM.today(), TM.stringToDate("$year-$month-$day"))
//
//            })

        mDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "CANCEL",
            DialogInterface.OnClickListener { dialog, which ->
                binding.datePicker.setTextColor(ContextCompat.getColor(this, R.color.MainGreen))
                binding.datePicker.setBackgroundColor(
                    ContextCompat.getColor(
                        this,
                        R.color.white
                    )
                )
                binding.save.isEnabled = false
            })


    }
}
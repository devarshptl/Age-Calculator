package com.devapat.minutecalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val selectDateBtn = findViewById<Button>(R.id.selectDateBtn)
        val selectedDateView = findViewById<TextView>(R.id.selectedDateView)
        val valInMinutes = findViewById<TextView>(R.id.valInMinutes)

        selectDateBtn.setOnClickListener{
            selectDate(selectedDateView, valInMinutes)
        }
    }

    fun selectDate(selectedDateView: TextView, valInMinutes: TextView) {
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ _, selectedYear, selectedMonth, selectedDay ->

            val selectedDateString = "$selectedDay/${selectedMonth+1}/$selectedYear"
            selectedDateView.text = selectedDateString

            val sdf = SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH)
            val dateObj = sdf.parse(selectedDateString)
            val selectedDateInMin = dateObj?.time?.div(60000)
            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateInMin = currentDate?.time?.div( 60000)
            valInMinutes.text = (currentDateInMin!! - selectedDateInMin!!).div(60).toString()
        }, year, month, day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}
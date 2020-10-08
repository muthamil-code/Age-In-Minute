package com.tam.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         dateButton.setOnClickListener { view ->
             createDate(view)
         }
    }
         fun createDate(view : View){
             val myCalender = Calendar.getInstance()
             val year = myCalender.get(Calendar.YEAR)
             val month = myCalender.get(Calendar.MONTH)
             val day = myCalender.get(Calendar.DAY_OF_MONTH)
             val dpd =   DatePickerDialog(this,DatePickerDialog.OnDateSetListener { view, selectedYear,selectedMonth , selectedDay ->
              val selectedDate = "$selectedDay/${selectedMonth+1}/${selectedYear}"
                 tvSelectedDate.setText(selectedDate)
                 val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                 val date = sdf.parse(selectedDate)
                 val sdfDate = date!!.time/60000
                 val currentSdfDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                 val sdfCurrentValue = currentSdfDate!!.time/60000
                 val difference = sdfCurrentValue - sdfDate
                 tvSelectedDateInMinute.setText(difference.toString())
                 Toast.makeText(this,"$sdfDate,$sdfCurrentValue ",Toast.LENGTH_LONG).show()

             },year,month,day)

             dpd.datePicker.maxDate = Date().time - 86400000
             dpd.show()
         }

}



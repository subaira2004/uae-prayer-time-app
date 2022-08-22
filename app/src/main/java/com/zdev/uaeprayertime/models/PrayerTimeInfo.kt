package com.zdev.uaeprayertime.models

import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.toUpperCase
import com.zdev.uaeprayertime.R

data class PrayerTimeInfo(val prayerName: String,val prayerTime: String){

    var prayerTime12H: String =  ""
    init{
        if(prayerTime.isNotEmpty()){
            var prayerTimeParts = prayerTime.split(':')
            var hourPart = prayerTimeParts[0].toInt()
            var minutePart =prayerTimeParts[1].toInt()
            val amPm =  if (hourPart>=12) "PM" else "AM"
                if (hourPart>12){
                    hourPart = hourPart -12
                }
            var hourText = if (hourPart>=10) "" else "0"
            var minuuteText = if (minutePart>=10) "" else "0"
            prayerTime12H = "${hourText}${hourPart}:${minuuteText}${minutePart} ${amPm}"
        }
    }
}

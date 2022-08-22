package com.zdev.uaeprayertime.services

import com.zdev.uaeprayertime.models.PrayerTimeInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import kotlinx.coroutines.*

class PrayerTimeService() {

    suspend fun collectPrayerTimes():List<PrayerTimeInfo>{
        val doc: Document  = getDoc("https://www.khaleejtimes.com/prayer-time-uae")

        var prayerTimeInfoLst = arrayListOf<PrayerTimeInfo>()
        prayerTimeInfoLst.add(PrayerTimeInfo(prayerName = "Fajr", prayerTime = doc.select("#nav_pt_calculate_fajr").text()))
        prayerTimeInfoLst.add(PrayerTimeInfo(prayerName = "Dhuhr", prayerTime = doc.select("#nav_pt_calculate_dhuhr").text()))
        prayerTimeInfoLst.add(PrayerTimeInfo(prayerName = "Asr", prayerTime = doc.select("#nav_pt_calculate_asr").text()))
        prayerTimeInfoLst.add(PrayerTimeInfo(prayerName = "Maghrib", prayerTime = doc.select("#nav_pt_calculate_magrib").text()))
        prayerTimeInfoLst.add(PrayerTimeInfo(prayerName = "Isha", prayerTime = doc.select("#nav_pt_calculate_isha").text()))
        return prayerTimeInfoLst.toList()
    }
   private suspend fun getDoc(url: String) : Document {
         return Jsoup.connect(url).get()
    }
}
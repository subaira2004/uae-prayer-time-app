package com.zdev.uaeprayertime.services

import androidx.lifecycle.SavedStateHandle
import com.zdev.uaeprayertime.models.PrayerTimeInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import kotlinx.coroutines.*
import javax.inject.Inject

@Module
@InstallIn(ViewModelComponent::class)
object ViewModulePrayerTimeModule {
    @Provides
    @ViewModelScoped
    fun provideRepo() = PrayerTimeService();
}

class PrayerTimeServiceFactory @Inject constructor(
    val prayerTimeService: PrayerTimeService
)


class PrayerTimeService() {
    suspend fun collectPrayerTimes(): List<PrayerTimeInfo> {
        val doc: Document = getDoc("https://www.khaleejtimes.com/prayer-time-uae")

        var prayerTimeInfoLst = arrayListOf<PrayerTimeInfo>()
        prayerTimeInfoLst.add(
            PrayerTimeInfo(
                prayerName = "Fajr",
                prayerTime = doc.select("#nav_pt_calculate_fajr").text()
            )
        )
        prayerTimeInfoLst.add(
            PrayerTimeInfo(
                prayerName = "Sun Rise",
                prayerTime = doc.select("#nav_pt_calculate_shuruq").text()
            )
        )
        prayerTimeInfoLst.add(
            PrayerTimeInfo(
                prayerName = "Dhuhr",
                prayerTime = doc.select("#nav_pt_calculate_dhuhr").text()
            )
        )
        prayerTimeInfoLst.add(
            PrayerTimeInfo(
                prayerName = "Asr",
                prayerTime = doc.select("#nav_pt_calculate_asr").text()
            )
        )
        prayerTimeInfoLst.add(
            PrayerTimeInfo(
                prayerName = "Maghrib",
                prayerTime = doc.select("#nav_pt_calculate_magrib").text()
            )
        )
        prayerTimeInfoLst.add(
            PrayerTimeInfo(
                prayerName = "Isha",
                prayerTime = doc.select("#nav_pt_calculate_isha").text()
            )
        )
        return prayerTimeInfoLst.toList()
    }

    private suspend fun getDoc(url: String): Document {
        return Jsoup.connect(url).get()
    }
}
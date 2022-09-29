package com.zdev.uaeprayertime.models

import android.app.appsearch.StorageInfo
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zdev.uaeprayertime.services.PrayerTimeService
import com.zdev.uaeprayertime.services.PrayerTimeServiceFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PrayerTimeViewModel @Inject constructor(
        val prayerTimeService:PrayerTimeServiceFactory
):ViewModel() {
    var prayerInfoList: List<PrayerTimeInfo> by mutableStateOf(listOf())
    var errMessage:String by mutableStateOf("")

    fun collectPrayerTime() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                prayerInfoList = prayerTimeService.prayerTimeService.collectPrayerTimes()
            }
            catch (e:Exception){
                errMessage = e.message.toString()
            }
        }
    }
}
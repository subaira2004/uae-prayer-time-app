package com.zdev.uaeprayertime


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zdev.uaeprayertime.models.PrayerTimeViewModel
import com.zdev.uaeprayertime.view.HomeUi


class MainActivity : ComponentActivity() {
    val prayerTimeViewModel by viewModels<PrayerTimeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeUi().HomeView(prayerTimeViewModel)
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun HomeUiPreview() {
        HomeUi().HomeView(prayerTimeViewModel)
    }

}

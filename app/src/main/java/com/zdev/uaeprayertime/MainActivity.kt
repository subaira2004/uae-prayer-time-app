package com.zdev.uaeprayertime


import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.zdev.uaeprayertime.models.PrayerTimeViewModel
import com.zdev.uaeprayertime.view.HomeUi
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class UaePrayerTimeApp : Application(){}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val prayerTimeViewModel: PrayerTimeViewModel by viewModels()

    //val prayerTimeViewModel by viewModels<PrayerTimeViewModel>()
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

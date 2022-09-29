package com.zdev.uaeprayertime.view


import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.zdev.uaeprayertime.R
import com.zdev.uaeprayertime.models.PrayerTimeInfo
import com.zdev.uaeprayertime.models.PrayerTimeViewModel
import com.zdev.uaeprayertime.ui.theme.UaePrayerTimeTheme

class HomeUi() {


    @Composable
    fun HomeView(prayerTimeViewModel: PrayerTimeViewModel) {

        UaePrayerTimeTheme {
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                //color = MaterialTheme.colors.background
                color = colorResource(id = R.color.page_bg)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    Text(
                        text = stringResource(R.string.Uae_Prayer_Time),
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.page_header_text), modifier = Modifier
                            .background(colorResource(R.color.page_header))
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                    PrayerList(prayerTimeViewModel.prayerInfoList)

                    prayerTimeViewModel.collectPrayerTime()
                }
            }
        }
    }

    @Composable
    private fun PrayerList(prayerInfoList: List<PrayerTimeInfo>
    ) {

        Column(
            verticalArrangement = Arrangement.Center, modifier = Modifier
                .fillMaxHeight()
                .padding(start = 10.dp, end = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            //val coroutineScope = rememberCoroutineScope()
           // var prayerInfoList =    prayerTimeService.collectPrayerTimes()
            var i = 1;
            for (n in prayerInfoList) {
                PrayerCard(
                    n.prayerName,
                    prayerTime = n.prayerTime12H,
                    prayerIconResId = getPrayerIconId(n.prayerName),
                    isNextPrayer = i == 3
                )
                i++
            }
        }

    }

    private fun getPrayerIconId(prayerName: String): Int {
        var prayerIconId: Int = 0
        if (prayerName.isNotEmpty()) {
            when (prayerName.uppercase()) {
                /*context.getString(R.string.fajr).uppercase() -> prayerIconId = R.drawable.fajr
                context.getString(R.string.dhuhr).uppercase() -> prayerIconId = R.drawable.dhuhr
                context.getString(R.string.asr).uppercase() -> prayerIconId = R.drawable.asr
                context.getString(R.string.maghrib).uppercase() -> prayerIconId = R.drawable.magrib
                context.getString(R.string.isha).uppercase() -> prayerIconId = R.drawable.isha
                */

                "fajr".uppercase() -> prayerIconId = R.drawable.fajr
                "dhuhr".uppercase() -> prayerIconId = R.drawable.dhuhr
                "asr".uppercase() -> prayerIconId = R.drawable.asr
                "maghrib".uppercase() -> prayerIconId = R.drawable.magrib
                "isha".uppercase() -> prayerIconId = R.drawable.isha
                "sun rise".uppercase() -> prayerIconId = R.drawable.shuruq
            }
        }
        return prayerIconId
    }

    @Composable
    private fun PrayerCard(
        prayerName: String, prayerTime: String,
        isNextPrayer: Boolean = false, prayerIconResId: Int
    ) {
        val prayerIcon: Painter = painterResource(id = prayerIconResId)

        var prayerCardBgColor = colorResource(id = R.color.prayer_card_bg)
        var prayerCardNameColor = colorResource(id = R.color.prayer_card_name)
        var prayerCardNameBgColor = colorResource(id = R.color.prayer_card_name_bg)
        var prayerCardTimeColor = colorResource(id = R.color.prayer_card_time)
        if (isNextPrayer) {
            prayerCardBgColor = colorResource(id = R.color.prayer_next_card_bg)
            prayerCardNameColor = colorResource(id = R.color.prayer_next_card_name)
            prayerCardNameBgColor = colorResource(id = R.color.prayer_next_card_name_bg)
            prayerCardTimeColor = colorResource(id = R.color.prayer_next_card_time)
        }
        Card(
            elevation = 10.dp,
            contentColor = Color.Blue,
            modifier = Modifier
                .padding(10.dp)
                .background(color = prayerCardBgColor)
        ) {
            Column(
                modifier = Modifier
                    .background(prayerCardBgColor)
            ) {
                Text(
                    text = prayerName,
                    color = prayerCardNameColor,
                    fontSize = 45.sp,

                    modifier = Modifier
                        .padding(5.dp)
                        .background(prayerCardNameBgColor)
                        .fillMaxWidth()
                        .padding(start = 30.dp, top = 10.dp, bottom = 1.dp)
                )
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = prayerIcon, contentDescription = null,
                        modifier = Modifier
                            .size(size = 60.dp)
                            .padding(start = 20.dp, top = 15.dp, bottom = 5.dp)
                    )
                    Text(
                        text = prayerTime,
                        color = prayerCardTimeColor,
                        fontSize = 45.sp,
                        textAlign = TextAlign.End,
                        modifier = Modifier
                            .padding(top = 5.dp, end = 30.dp)
                            .fillMaxWidth()
                    )
                }
            }
        }
    }


}

package com.timrashard.weathr.presentation.weathr.home.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.bodyFontFamily

@Composable
fun FooterSection() {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment  = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(20.dp))
            .background(color = MaterialTheme.colorScheme.secondaryContainer)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.data_sourced_from),
            fontFamily = bodyFontFamily,
            color = Color.Gray,
            fontSize = 12.sp
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            FooterItem(
                iconId = R.drawable.visualcrossing_64,
                title = stringResource(R.string.visualcrossing)
            )
            FooterItem(
                iconId = R.drawable.openweather_64,
                title = stringResource(R.string.openWeather)
            )
        }
    }
}

@Composable
fun FooterItem(
    iconId: Int,
    title: String
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "Source Icon",
            modifier = Modifier
                .size(24.dp)
        )

        Text(
            text = title,
            fontFamily = bodyFontFamily,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 14.sp
        )
    }
}
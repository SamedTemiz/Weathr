package com.timrashard.weathr.presentation.weathr.home.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.data.model.CurrentConditions
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily

@Composable
fun TemperatureSection(
    currentConditions: CurrentConditions
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp)
    ) {
        Column {
            Text(
                text = "${currentConditions.temp.toInt()}°",
                style = TextStyle(
                    fontSize = 96.sp,
                    fontFamily = displayFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
            Text(
                text = currentConditions.conditions,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = Color.Gray
                )
            )
        }

        Image(
            painter = painterResource(id = R.drawable.gunes),
            contentDescription = "",
            modifier = Modifier.size(150.dp)
        )
    }
}

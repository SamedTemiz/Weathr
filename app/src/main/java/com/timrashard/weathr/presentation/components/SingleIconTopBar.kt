package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily

@Composable
fun SingleIconTopBar(
    title: String,
    subtitle: String,
    onMenuClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        LocationAndDate(title, subtitle)
        CustomIconButton(
            onClick = {
                onMenuClick()
            },
            icon = R.drawable.ic_menu_24,
            contentDesc = "Menu"
        )
    }
}

@Composable
fun LocationAndDate(
    location: String,
    date: String
) {
    Column {
        Text(
            text = location,
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = displayFontFamily,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.tertiary
            )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = date,
            style = TextStyle(
                fontSize = 18.sp,
                fontFamily = bodyFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        )
    }
}
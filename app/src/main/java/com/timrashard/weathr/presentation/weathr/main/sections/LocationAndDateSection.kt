package com.timrashard.weathr.presentation.weathr.main.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.AppTypography
import com.timrashard.weathr.presentation.theme.WeathrTheme

@Composable
fun LocationAndDateSection() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        LocationAndDate()
        DetailsButton()
    }
}

@Composable
fun LocationAndDate() {
    val headerStyle = AppTypography.headlineMedium
    val subHeaderStyle = AppTypography.bodyLarge

    Column {
        Text(
            text = "Stuttgart",
            style = TextStyle(
                fontSize = headerStyle.fontSize,
                fontFamily = headerStyle.fontFamily,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.tertiary
            )
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "12 September, Sunday",
            style = TextStyle(
                fontSize = subHeaderStyle.fontSize,
                fontFamily = subHeaderStyle.fontFamily,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        )
    }
}

@Composable
fun DetailsButton() {
    IconButton(
        onClick = { /* Menü açma işlemi */ },
        modifier = Modifier
            .size(48.dp)
            .background(color = Color(0xFF202329), shape = RoundedCornerShape(15.dp))
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu_24),
            tint = Color.LightGray,
            contentDescription = "Menu",
        )
    }
}

@Preview
@Composable
fun LocationAndDateSectionPreview() {
    WeathrTheme {
        LocationAndDateSection()
    }
}
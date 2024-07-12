package com.timrashard.weathr.presentation.weathr.main.sections

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.presentation.theme.WeathrTheme

@Composable
fun WeatherMapSection() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color(0xFF202329)),
        contentAlignment = Alignment.Center
    ) {
        // Placeholder for the map image
        Text(text = "Map", style = TextStyle(color = Color.White, fontSize = 18.sp))
    }
}

@Preview
@Composable
fun WeatherMapSectionPreview() {
    WeathrTheme {
        WeatherMapSection()
    }
}
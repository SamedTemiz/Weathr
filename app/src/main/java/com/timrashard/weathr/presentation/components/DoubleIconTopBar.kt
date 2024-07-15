package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.AppTypography

@Composable
fun DoubleIconTopBar(
    title: String,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        CustomIconButton(
            modifier = Modifier,
            onClick = {
                onBackClick()
            },
            icon = R.drawable.ic_back_24,
            contentDesc = "Menu",
        )

        Text(
            text = title,
            style = TextStyle(
                fontSize = AppTypography.headlineMedium.fontSize,
                fontFamily = AppTypography.headlineMedium.fontFamily,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.tertiary
            )
        )

        CustomIconButton(
            modifier = Modifier,
            onClick = {
                onSettingsClick()
            },
            icon = R.drawable.ic_settings_24,
            contentDesc = "Menu"
        )
    }
}
package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun CustomIconButton(
    modifier: Modifier,
    onClick: () -> Unit,
    icon: Int,
    contentDesc: String? = null
){
    IconButton(
        onClick = { onClick() },
        modifier = modifier
            .size(48.dp)
            .background(color = Color(0xFF202329), shape = RoundedCornerShape(15.dp))
    ) {
        Icon(
            painter = painterResource(id = icon),
            tint = Color.LightGray,
            contentDescription = contentDesc,
            modifier = Modifier.size(24.dp)
        )
    }
}
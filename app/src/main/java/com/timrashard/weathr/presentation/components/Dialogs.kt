package com.timrashard.weathr.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.AppTypography
import com.timrashard.weathr.presentation.theme.WeathrTheme
import com.timrashard.weathr.presentation.theme.bodyFontFamily


@Preview
@Composable
fun DialogPreview() {
    WeathrTheme {
        NetworkStatusDialog(
            title = "DENEME",
            message = "burada hata mesajı hözüekdsg",
            onRetry = { /*TODO*/ }) {

        }
    }
}

@Composable
fun NetworkStatusDialog(
    title: String,
    message: String,
    onRetry: () -> Unit,
    onExit: () -> Unit
) {
    var isNetworkDialogVisible by remember { mutableStateOf(true) }

    if (isNetworkDialogVisible) {
        AlertDialog(
            onDismissRequest = { onExit() },
            onConfirmation = {
                onRetry()
                isNetworkDialogVisible = false
            },
            dialogTitle = title,
            dialogText = message,
            dismissText = "Exit",
            confirmText = "Retry",
            icon = ImageVector.vectorResource(id = R.drawable.ic_wifi_24)
        )
    }
}

@Composable
fun AlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    confirmText: String = "Confirm",
    dismissText: String = "Dismiss",
    icon: ImageVector = Icons.Default.Info,
) {
    AlertDialog(
        icon = { Icon(icon, contentDescription = "Icon", tint = MaterialTheme.colorScheme.tertiary) },
        title = {
            Text(text = dialogTitle)
        },
        text = {
            Text(text = dialogText, style = TextStyle(
                fontFamily = bodyFontFamily,
                fontSize = 16.sp
            ))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(confirmText, color = MaterialTheme.colorScheme.tertiary, style = TextStyle(
                    fontFamily = bodyFontFamily,
                    fontSize = 16.sp
                ))
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(dismissText, color = MaterialTheme.colorScheme.error, style = TextStyle(
                    fontFamily = bodyFontFamily,
                    fontSize = 16.sp
                ))
            }
        },
        containerColor = MaterialTheme.colorScheme.secondary,
        textContentColor = MaterialTheme.colorScheme.tertiary,
        titleContentColor = MaterialTheme.colorScheme.tertiary
    )
}

@Composable
fun ProgressDialog(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Black.copy(alpha = 0.5f),
    progressColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(
            color = progressColor,
            modifier = Modifier.size(64.dp)
        )
    }
}
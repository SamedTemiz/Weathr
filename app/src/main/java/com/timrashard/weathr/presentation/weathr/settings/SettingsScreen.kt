package com.timrashard.weathr.presentation.weathr.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.components.CustomIconButton
import com.timrashard.weathr.presentation.components.LocationAndDate
import com.timrashard.weathr.presentation.theme.AppTypography
import com.timrashard.weathr.presentation.theme.WeathrTheme
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily
import com.timrashard.weathr.presentation.weathr.Screen

@Composable
fun SettingsScreen(navController: NavController) {
    var isMetric by remember { mutableStateOf(true) }
    var selectedLanguage by remember { mutableStateOf("English") }
    var locationEnabled by remember { mutableStateOf(true) }

    Surface(
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            SettingsTopBar(navController)
            Text(
                text = "Language",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
            LanguageDropdown(selectedLanguage) { selectedLanguage = it }


            Text(
                text = "Units",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Metric")
                Switch(
                    checked = isMetric,
                    onCheckedChange = { isMetric = it }
                )
                Text("Imperial")
            }

            Text("Location", style = TextStyle(fontSize = 18.sp))
            Switch(
                checked = locationEnabled,
                onCheckedChange = { locationEnabled = it }
            )
            Text(if (locationEnabled) "Enabled" else "Disabled")
        }
    }
}

@Composable
fun SettingsTopBar(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        CustomIconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {
                navController.navigateUp()
            },
            icon = R.drawable.ic_back_24,
            contentDesc = "Menu"
        )
        Text(
            text = "Settings",
            style = TextStyle(
                fontSize = 28.sp,
                fontFamily = displayFontFamily,
                color = MaterialTheme.colorScheme.tertiary
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun LanguageDropdown(selectedLanguage: String, onLanguageSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val languages = listOf("English", "Spanish", "French", "German")

    Box(modifier = Modifier.fillMaxWidth()) {
        Button(onClick = { expanded = true }) {
            Text(
                selectedLanguage, style = TextStyle(
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    onClick = {
                        onLanguageSelected(language)
                        expanded = false
                    }, text = {
                        Text(
                            language, style = TextStyle(
                                color = MaterialTheme.colorScheme.tertiary
                            )
                        )
                    })
            }
        }
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    WeathrTheme {
        SettingsScreen(navController = rememberNavController())
    }
}

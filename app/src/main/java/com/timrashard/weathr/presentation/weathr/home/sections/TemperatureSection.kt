package com.timrashard.weathr.presentation.weathr.home.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.timrashard.weathr.R
import com.timrashard.weathr.common.Constant.ASSETS_BASE_URL
import com.timrashard.weathr.data.model.CurrentConditions
import com.timrashard.weathr.presentation.components.ParallaxEffect
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily
import com.timrashard.weathr.presentation.weathr.WeatherViewModel
import kotlinx.coroutines.launch

@Composable
fun TemperatureSection(
    viewModel: WeatherViewModel,
    currentConditions: CurrentConditions
) {
    val iconName by remember { mutableStateOf(currentConditions.icon) }
    val rawComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.loading))
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.loadIcon(iconName)
        }
    }

    val iconUrl by viewModel.iconUrl.collectAsState()

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
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

        iconUrl?.let { url ->
            ParallaxEffect(
                modifier = Modifier
                    .fillMaxWidth(0.40f)
                    .aspectRatio(1f)
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Weather Icon",
                    loading = {
                        LottieAnimation(
                            composition = rawComposition,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit,
                        )
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        } ?: run {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth(0.40f)
                    .aspectRatio(1f),

                ) {
                LottieAnimation(
                    composition = rawComposition,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit,
                    iterations = LottieConstants.IterateForever
                )
            }
        }
    }
}

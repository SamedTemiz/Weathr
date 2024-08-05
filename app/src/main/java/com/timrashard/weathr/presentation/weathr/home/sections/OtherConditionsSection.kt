package com.timrashard.weathr.presentation.weathr.home.sections

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.timrashard.weathr.R
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily

@Composable
fun OtherConditionsSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            WindDetailsCard(Modifier.weight(1f))

            Spacer(modifier = Modifier.height(16.dp))

            SunDetailsCard(Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.width(16.dp))

        ConditionsDetailsCard(Modifier.weight(1f))
    }
}

@Composable
fun WindDetailsCard(modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Column(
            modifier = Modifier.weight(1f).padding(start = 16.dp)
        ) {
            Text(
                text = "North",
                fontFamily = displayFontFamily,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary
            )

            Text(
                text = "137 m/s",
                fontFamily = displayFontFamily,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        Compass(
            modifier = Modifier
                .padding(16.dp)
                .weight(1f),
            windDirection = 67.0f // Örnek rüzgar yönü
        )
    }
}

@Composable
fun SunDetailsCard(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        val sunRiseSet by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.sun_rise_set))

        LottieAnimation(
            composition = sunRiseSet,
            iterations = LottieConstants.IterateForever,
            speed = 3f,
            modifier = Modifier
                .align(Alignment.BottomStart)
                .fillMaxSize()
                .graphicsLayer(alpha = 0.5f)
        )

        val sunRise = buildAnnotatedString {
            append("06:18")
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily
                )
            ) {
                append(" Sunrise")
            }
        }

        val sunSet = buildAnnotatedString {
            append("20:18")
            withStyle(
                style = SpanStyle(
                    fontSize = 18.sp,
                    fontFamily = bodyFontFamily
                )
            ) {
                append(" Sunset")
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterStart)
        ) {
            Text(
                text = sunRise,
                fontFamily = displayFontFamily,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = sunSet,
                fontFamily = displayFontFamily,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun ConditionsDetailsCard(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Green, shape = RoundedCornerShape(20.dp))
    ) {

    }
}

@Composable
fun Compass(
    modifier: Modifier = Modifier,
    windDirection: Float
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .aspectRatio(1f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.compass_bg),
            contentDescription = "Compass",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxSize()
        )

        // Ok resmini ekleyip döndür
        Image(
            painter = painterResource(id = R.drawable.compass_arrow),
            contentDescription = "Arrow",
            contentScale = ContentScale.None,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .rotate(windDirection)
        )
    }
}
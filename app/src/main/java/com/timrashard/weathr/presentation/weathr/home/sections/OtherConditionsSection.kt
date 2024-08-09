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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
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
import com.timrashard.weathr.data.model.CurrentConditions
import com.timrashard.weathr.presentation.components.CircularProgressComponent
import com.timrashard.weathr.presentation.theme.bodyFontFamily
import com.timrashard.weathr.presentation.theme.displayFontFamily
import com.timrashard.weathr.utils.DateTimeUtils
import com.timrashard.weathr.utils.MathUtils

@Composable
fun OtherConditionsSection(
    currentConditions: CurrentConditions?
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        currentConditions?.let { data ->
            Column(
                modifier = Modifier.weight(1f)
            ) {
                WindDetailsCard(
                    windDir = data.winddir.toFloat(),
                    windSpeed = data.windspeed.toInt().toString(),
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.height(8.dp))

                SunDetailsCard(
                    sunRiseValue = DateTimeUtils.formatTime(data.sunrise, "HH:mm"),
                    sunSetValue = DateTimeUtils.formatTime(data.sunset, "HH:mm"),
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            ConditionsDetailsCard(
                feelsLike = data.feelslike.toInt().toString(),
                solarRadiation = data.solarradiation.toInt().toString(),
                uvIndex = data.uvindex.toInt().toString(),
                pressure = data.pressure.toInt().toString(),
                visibility = data.visibility.toString(),
                modifier = Modifier.weight(1f)
            )
        } ?: run {
            CircularProgressComponent()
        }
    }
}

@Composable
fun WindDetailsCard(
    windDir: Float,
    windSpeed: String,
    modifier: Modifier
) {
    val mathUtils = MathUtils(LocalContext.current)

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
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp)
        ) {
            Text(
                text = mathUtils.getAngleDirection(windDir),
                fontFamily = bodyFontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary
            )

            Text(
                text = "$windSpeed m/s",
                fontFamily = displayFontFamily,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }

        Compass(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
                .shadow(10.dp, shape = CircleShape),
            windDirection = mathUtils.calculateOppositeAngle(windDir)
        )
    }
}

@Composable
fun SunDetailsCard(
    sunRiseValue: String,
    sunSetValue: String,
    modifier: Modifier
) {
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
            append(sunRiseValue)
            withStyle(
                style = SpanStyle(
                    fontSize = 16.sp,
                    fontFamily = bodyFontFamily
                )
            ) {
                append(" ${stringResource(id = R.string.sunrise)}")
            }
        }

        val sunSet = buildAnnotatedString {
            append(sunSetValue)
            withStyle(
                style = SpanStyle(
                    fontSize = 16.sp,
                    fontFamily = bodyFontFamily
                )
            ) {
                append(" ${stringResource(id = R.string.sunset)}")
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
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
            Text(
                text = sunSet,
                fontFamily = displayFontFamily,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
fun ConditionsDetailsCard(
    feelsLike: String,
    solarRadiation: String,
    uvIndex: String,
    pressure: String,
    visibility: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(20.dp)
            )
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ConditionDetailsItem(
                title = stringResource(id = R.string.feels_like),
                value = "$feelsLike°C"
            )
            ConditionDetailsItem(
                title = stringResource(id = R.string.solar_radiation),
                value = solarRadiation
            )
            ConditionDetailsItem(
                title = stringResource(id = R.string.uv_index),
                value = uvIndex
            )
            ConditionDetailsItem(
                title = stringResource(id = R.string.pressure),
                value = "$pressure mb"
            )
            ConditionDetailsItem(
                title = stringResource(id = R.string.visibility),
                value = "$visibility km"
            )
        }
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
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = painterResource(id = R.drawable.compass_arrow),
            contentDescription = "Arrow",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxSize()
                .rotate(windDirection)
        )
    }
}


@Composable
fun ConditionDetailsItem(title: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = title,
                fontFamily = bodyFontFamily,
                fontSize = 14.sp,
                color = Color.Gray
            )

            Text(
                text = value,
                fontFamily = bodyFontFamily,
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.tertiary
            )
        }
        HorizontalDivider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}
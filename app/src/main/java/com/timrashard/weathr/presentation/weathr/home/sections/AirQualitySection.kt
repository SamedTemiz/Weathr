package com.timrashard.weathr.presentation.weathr.home.sections

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.timrashard.weathr.R
import com.timrashard.weathr.common.Resource
import com.timrashard.weathr.data.model.air.toCurrentAirPollution
import com.timrashard.weathr.presentation.components.CircularProgressComponent
import com.timrashard.weathr.presentation.components.CurrentAirPollution
import com.timrashard.weathr.presentation.weathr.WeatherViewModel

@Composable
fun AirQualitySection(viewModel: WeatherViewModel) {
    val airState by viewModel.airData.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        LottieBackground()

        Column {
            AirQualityHeader(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 16.dp, end = 16.dp)
            )

            when (airState) {
                is Resource.Loading -> {
                    CircularProgressComponent()
                }

                is Resource.Success -> {
                    val airData = airState.data

                    airData?.let { data ->
                        CurrentAirPollution(data.toCurrentAirPollution())
                    }
                }

                is Resource.Error -> {
                    Text("Error: ${airState.message}")
                }
            }
        }
    }
}

@Composable
fun AirQualityHeader(modifier: Modifier) {
    val emojiList = listOf(
        R.drawable.good,
        R.drawable.fair,
        R.drawable.moderate,
        R.drawable.poor,
        R.drawable.very_poor,
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        Row{
            Image(
                painter = painterResource(id = R.drawable.leaf),
                contentDescription = "Leaf Icon",
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.air_quality),
                color = Color.Gray,
                fontSize = 16.sp
            )
        }
        
        Row(
            horizontalArrangement = Arrangement.End,
        ){
            emojiList.forEach{ emoji ->
                Spacer(Modifier.width(4.dp))
                Image(
                    painter = painterResource(id = emoji),
                    contentDescription = "Emoji",
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }
}

@Composable
fun LottieBackground() {
    val bottomLeft by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.bg_left_corner))
    val topRight by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.bg_right_corner))

    val speed by remember { mutableFloatStateOf(1.5f) }

    Box(Modifier.fillMaxSize()) {
//        LottieAnimation(
//            composition = bottomLeft,
//            iterations = LottieConstants.IterateForever,
//            contentScale = ContentScale.FillBounds,
//            speed = speed,
//            modifier = Modifier
//                .align(Alignment.BottomStart)
//                .clip(RoundedCornerShape(topStart = 15.dp, bottomStart = 15.dp))
//                .fillMaxSize()
//        )

        LottieAnimation(
            composition = topRight,
            iterations = LottieConstants.IterateForever,
            contentScale = ContentScale.FillBounds,
            speed = speed,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(topEnd = 15.dp, bottomEnd = 15.dp))
                .fillMaxSize()
        )
    }
}
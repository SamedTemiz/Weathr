package com.timrashard.weathr.presentation.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.timrashard.weathr.presentation.theme.WeathrTheme

@Composable
fun AnimatedShimmer() {
    val shimmerColors = listOf(
        Color.LightGray.copy(alpha = 0.6f),
        Color.LightGray.copy(alpha = 0.2f),
        Color.LightGray.copy(alpha = 0.6f)
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            )
        ),
        label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColors,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ShimmerScreen(brush = brush)
}

@Composable
fun ShimmerScreen(brush: Brush) {
    Column(
        verticalArrangement = Arrangement.spacedBy(32.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary)
            .padding(16.dp)
    ) {
        TopShimmer(brush)

        TemperatureShimmer(brush)

        WeatherDetailsShimmer(brush)

        ForecastShimmer(brush)

        BottomShimmer(brush)
    }
}

@Composable
fun TopShimmer(brush: Brush) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxHeight()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(25.dp)
                    .background(brush)
            )
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .height(15.dp)
                    .background(brush)
            )
        }

        Spacer(
            modifier = Modifier
                .size(48.dp)
                .background(brush, shape = RoundedCornerShape(15.dp))
        )
    }
}

@Composable
fun TemperatureShimmer(brush: Brush) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .height(75.dp)
                    .background(brush)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .height(15.dp)
                    .background(brush)
            )
        }

        Spacer(
            modifier = Modifier
                .size(100.dp)
                .background(brush, shape = RoundedCornerShape(15.dp))
        )
    }
}

@Composable
fun WeatherDetailsShimmer(brush: Brush) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.secondaryContainer, shape = RoundedCornerShape(20.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 18.dp)

        ) {
            Spacer(
                modifier = Modifier
                    .size(75.dp, 100.dp)
                    .background(brush, shape = RoundedCornerShape(15.dp))
            )
            Spacer(
                modifier = Modifier
                    .size(75.dp, 100.dp)
                    .background(brush, shape = RoundedCornerShape(15.dp))
            )
            Spacer(
                modifier = Modifier
                    .size(75.dp, 100.dp)
                    .background(brush, shape = RoundedCornerShape(15.dp))
            )
        }
    }
}

@Composable
fun ForecastShimmer(brush: Brush) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(15.dp)
                    .background(brush)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(15.dp)
                    .background(brush)
            )
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .height(15.dp)
                    .background(brush)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()

            ) {
                Spacer(
                    modifier = Modifier
                        .size(75.dp, 100.dp)
                        .background(brush, shape = RoundedCornerShape(15.dp))
                )
                Spacer(
                    modifier = Modifier
                        .size(75.dp, 100.dp)
                        .background(brush, shape = RoundedCornerShape(15.dp))
                )
                Spacer(
                    modifier = Modifier
                        .size(75.dp, 100.dp)
                        .background(brush, shape = RoundedCornerShape(15.dp))
                )
                Spacer(
                    modifier = Modifier
                        .size(75.dp, 100.dp)
                        .background(brush, shape = RoundedCornerShape(15.dp))
                )
            }
        }
    }
}

@Composable
fun BottomShimmer(brush: Brush){
    Spacer(modifier = Modifier
        .fillMaxSize()
        .background(brush, shape = RoundedCornerShape(15.dp)))
}

@Preview
@Composable
fun ShimmerPreview() {
    WeathrTheme {
        AnimatedShimmer()
    }
}
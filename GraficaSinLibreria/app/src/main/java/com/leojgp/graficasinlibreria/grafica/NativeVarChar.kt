package com.leojgp.graficasinlibreria.grafica

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GraficaRectangular(
    data: List<Pair<String, Float>>,
    maxValue: Float,
    modifier: Modifier = Modifier
) {
    var animated by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = true) {
        animated = true
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Gráfica Rectangular",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            data.forEach { (label, value) ->
                val animatedHeight by animateFloatAsState(
                    targetValue = if (animated) value / maxValue else 0f,
                    animationSpec = tween(durationMillis = 1000),
                    label = "height"
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Bottom,
                    modifier = Modifier.weight(1f)
                ) {
                    Box(
                        modifier = Modifier
                            .height(200.dp)
                            .padding(horizontal = 8.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.BottomCenter
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(animatedHeight)
                                .clip(RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp))
                                .background(MaterialTheme.colorScheme.primary)
                        )
                    }

                    Text(
                        text = label,
                        modifier = Modifier.padding(top = 8.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp
                    )

                    Text(
                        text = value.toString(),
                        modifier = Modifier.padding(top = 4.dp),
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun GraficaLineal(
    dataPoints: List<Pair<Float, Float>>,
    modifier: Modifier = Modifier,
    lineColor: Color = MaterialTheme.colorScheme.primary
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(16.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            val minX = dataPoints.minByOrNull { it.first }?.first ?: 0f
            val maxX = dataPoints.maxByOrNull { it.first }?.first ?: 1f
            val minY = dataPoints.minByOrNull { it.second }?.second ?: 0f
            val maxY = dataPoints.maxByOrNull { it.second }?.second ?: 1f

            drawLine(
                color = Color.Gray,
                start = Offset(0f, height),
                end = Offset(width, height),
                strokeWidth = 2f
            )

            drawLine(
                color = Color.Gray,
                start = Offset(0f, 0f),
                end = Offset(0f, height),
                strokeWidth = 2f
            )

            if (dataPoints.size > 1) {
                for (i in 0 until dataPoints.size - 1) {
                    val startX = ((dataPoints[i].first - minX) / (maxX - minX)) * width
                    val startY = height - ((dataPoints[i].second - minY) / (maxY - minY)) * height
                    val endX = ((dataPoints[i + 1].first - minX) / (maxX - minX)) * width
                    val endY = height - ((dataPoints[i + 1].second - minY) / (maxY - minY)) * height

                    drawLine(
                        color = lineColor,
                        start = Offset(startX, startY),
                        end = Offset(endX, endY),
                        strokeWidth = 3f
                    )
                }
            }
        }
    }
}

@Composable
fun DatosGrafica() {
    val barData = listOf(
        "Ene" to 10f,
        "Feb" to 45f,
        "Mar" to 25f,
        "Abr" to 65f,
        "May" to 40f
    )

    val lineData = listOf(
        0f to 20f,
        1f to 45f,
        2f to 30f,
        3f to 65f,
        4f to 40f
    )

    Column(modifier = Modifier.fillMaxWidth()) {
        GraficaRectangular(
            data = barData,
            maxValue = 100f,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Gráfica Lineal",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        GraficaLineal(
            dataPoints = lineData,
            modifier = Modifier.padding(16.dp)
        )
    }
}

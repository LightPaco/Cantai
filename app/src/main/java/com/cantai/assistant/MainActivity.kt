package com.cantai.assistant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object CantaiColors {
    val Black = Color(0xFF050607)
    val White = Color(0xFFFFFFFF)
    val Cyan = Color(0xFF00E5FF)
    val Turquoise = Color(0xFF00FFD1)
    val Yellow = Color(0xFFFFE600)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { CantaiScreen() }
    }
}

@Composable
fun CantaiScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CantaiColors.Black)
            .systemBarsPadding()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AiCore()
        Spacer(Modifier.height(32.dp))
        Text(
            text = "CANTAI",
            color = CantaiColors.White,
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 8.sp
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = "FOUNDATION BUILD v0.1",
            color = CantaiColors.Yellow,
            fontSize = 12.sp,
            letterSpacing = 3.sp
        )
    }
}

@Composable
fun AiCore(modifier: Modifier = Modifier) {
    val transition = rememberInfiniteTransition(label = "core")
    val pulse by transition.animateFloat(
        initialValue = 0.92f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            tween(2200, easing = FastOutSlowInEasing),
            RepeatMode.Reverse
        ),
        label = "pulse"
    )
    val spin by transition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(tween(12000, easing = LinearEasing)),
        label = "spin"
    )

    Canvas(modifier = modifier.size(260.dp)) {
        val c = center
        val r = size.minDimension / 2

        drawCircle(
            brush = Brush.radialGradient(
                colors = listOf(
                    CantaiColors.Turquoise.copy(alpha = 0.55f),
                    CantaiColors.Cyan.copy(alpha = 0.18f),
                    Color.Transparent
                ),
                center = c,
                radius = r * pulse
            ),
            radius = r * pulse,
            center = c
        )
        drawCircle(CantaiColors.Turquoise, radius = r * 0.30f * pulse, center = c)
        drawCircle(
            CantaiColors.Cyan,
            radius = r * 0.62f,
            center = c,
            style = Stroke(width = 3.dp.toPx())
        )
        rotate(degrees = spin, pivot = c) {
            drawArc(
                color = CantaiColors.Yellow,
                startAngle = 0f,
                sweepAngle = 40f,
                useCenter = false,
                topLeft = Offset(c.x - r * 0.8f, c.y - r * 0.8f),
                size = Size(r * 1.6f, r * 1.6f),
                style = Stroke(width = 3.dp.toPx(), cap = StrokeCap.Round)
            )
        }
    }
}

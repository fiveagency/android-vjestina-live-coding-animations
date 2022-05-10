package five.denisbuketa.animations.ui

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import five.denisbuketa.animations.ui.theme.AndroidColor
import androidx.compose.ui.graphics.graphicsLayer as graphicsLayer1


@Composable
fun AnimateAsStateScreen() {
    LazyColumn(Modifier.padding(start = 16.dp, end = 16.dp)) {
        item { AnimateAlphaAnimation() }
    }
}

@Composable
private fun AnimateAlphaAnimation() {
    var enabled by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { enabled = enabled.not() }
        ) { Text("Click Me") }
    }

    // Animating float value between two values
    val alpha: Float by animateFloatAsState(if (enabled) 1f else 0f)
    Box(
        Modifier.size(100.dp)
            .graphicsLayer1(alpha = alpha)
            .background(AndroidColor)
    )
}
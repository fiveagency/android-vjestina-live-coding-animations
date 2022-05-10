package five.denisbuketa.animations.ui.theme

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun AnimatableScreen() {
    LazyColumn(Modifier.padding(start = 16.dp, end = 16.dp)) {
        item { AnimatableAnimation() }
        item { AnimatableSizeAnimation() }
    }
}

@Composable
private fun AnimatableAnimation() {
    var enabled by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { enabled = enabled.not() }
        ) { Text("Click Me") }
    }

    // defining the color as animatable
    val color = remember { Animatable(AndroidColor) }

    // defining coroutine scope that lives as long the key is not changed
    LaunchedEffect(enabled) {
        color.animateTo(if (enabled) AndroidColor else Purple200)
    }
    Box(
        Modifier.size(100.dp)
            .background(color.value)
    )
}

@Composable
private fun AnimatableSizeAnimation() {
    var enabled by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { enabled = enabled.not() }
        ) { Text("Click Me") }
    }

    // defining the width as animatable and specifying TwoWayConverter
    val width = remember { androidx.compose.animation.core.Animatable(100.dp, Dp.Companion.VectorConverter) }

    // defining coroutine scope that lives as long the key is not changed
    LaunchedEffect(enabled) {
        width.animateTo(if (enabled) 100.dp else 200.dp)
    }
    Box(
        Modifier
            .width(width.value)
            .height(100.dp)
            .background(AndroidColor)
    )
}
package five.denisbuketa.animations.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import five.denisbuketa.animations.R
import five.denisbuketa.animations.ui.theme.AndroidColor


@Composable
fun UpdateTransitionScreen() {
    LazyColumn(Modifier.padding(start = 16.dp, end = 16.dp)) {
        item { UpdateTransitionAnimation() }
    }
}

enum class BoxState {
    Collapsed,
    Expanded
}

@Composable
private fun UpdateTransitionAnimation() {
    var currentState by remember { mutableStateOf(BoxState.Collapsed) }
    val transition = updateTransition(currentState, label = "Box")

    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { currentState = if (currentState == BoxState.Collapsed) BoxState.Expanded else BoxState.Collapsed }
        ) { Text("Click Me") }
    }

    val size by transition.animateDp(label = "size") { state ->
        when (state) {
            BoxState.Collapsed -> 100.dp
            BoxState.Expanded -> 200.dp
        }
    }
    val borderWidth by transition.animateDp(label = "border") { state ->
        when (state) {
            BoxState.Collapsed -> 1.dp
            BoxState.Expanded -> 4.dp
        }
    }
    val color by transition.animateColor(
        transitionSpec = {
            when {
                BoxState.Expanded isTransitioningTo BoxState.Collapsed ->
                    spring()
                else ->
                    tween(durationMillis = 500)
            }
        }, label = "color"
    ) { state ->
        when (state) {
            BoxState.Collapsed -> MaterialTheme.colors.primary
            BoxState.Expanded -> AndroidColor
        }
    }
    val imageAlpha by transition.animateFloat(label = "alpha") { state ->
        when (state) {
            BoxState.Collapsed -> 0f
            BoxState.Expanded -> 1f
        }
    }

    Box(
        modifier = Modifier
            .size(size)
            .background(color)
            .border(borderWidth, Color.Black)
    ) {
        Image(
            painter = painterResource(id = R.drawable.jetpack),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
                .align(Alignment.Center)
                .alpha(imageAlpha)
        )
    }
}
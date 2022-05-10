package five.denisbuketa.animations.ui

import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import five.denisbuketa.animations.R


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityScreen() {
    LazyColumn(Modifier.padding(start = 16.dp, end = 16.dp)) {
        item { AnimationInColumn() }
        item { AnimationInRow() }
        item { FadeInOut() }
        item { SlideInSlideOutVertically() }
        item { ExpandInExpandOut() }
        item { ScaleInScaleOut() }
        item { DontAnimateChildren() }
        item { AnimateChildren() }
        item { Spacer(Modifier.height(300.dp)) }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationInColumn() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }


    Column(Modifier.height(160.dp)) {
        Image(
            painter = painterResource(id = R.drawable.jetpack),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFa4c639))
        )
        // ColumnScope
        // EnterTransition
        // ExitTransition
        AnimatedVisibility(visible = visible) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFa4c639))
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimationInRow() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }


    Row {
        Image(
            painter = painterResource(id = R.drawable.jetpack),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
                .clip(CircleShape)
                .background(Color(0xFFa4c639))
        )
        // RowScope
        // EnterTransition
        // ExitTransition
        AnimatedVisibility(visible = visible) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFa4c639))
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun FadeInOut() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .padding(2.dp)
            .size(80.dp)
            .clip(RectangleShape)
    ) {

        // Specified enter transition - fadeInt
        // Specified exit transition - fadeOut
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFa4c639))
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SlideInSlideOutVertically() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .padding(2.dp)
            .size(80.dp)
            .clip(RectangleShape)
    ) {

        // Specified enter transition - combining transitions
        // Specified exit transition - combining transitions
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(initialOffsetY = { fullHeight -> -fullHeight }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { fullHeight -> -fullHeight }) + fadeOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFa4c639))
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ExpandInExpandOut() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .padding(2.dp)
            .size(80.dp)
            .clip(RectangleShape),
        contentAlignment = Alignment.Center
    ) {

        // Specified enter transition - expand
        // Specified exit transition - shrink
        AnimatedVisibility(
            visible = visible,
            enter = expandIn(expandFrom = Alignment.Center),
            exit = shrinkOut(shrinkTowards = Alignment.Center)
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFa4c639))
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun ScaleInScaleOut() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .padding(2.dp)
            .size(80.dp)
            .clip(RectangleShape)
    ) {
        // Specified enter transition - expand
        // Specified exit transition - shrink
        AnimatedVisibility(
            visible = visible,
            enter = scaleIn(),
            exit = scaleOut()
        ) {
            Image(
                painter = painterResource(id = R.drawable.jetpack),
                contentDescription = null,
                modifier = Modifier.size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFa4c639))
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun DontAnimateChildren() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .padding(2.dp)
            .size(80.dp)
            .clip(RectangleShape),
        propagateMinConstraints = true
    ) {
        // All children use the same animation
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0xFFa4c639))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jetpack),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                        .clip(CircleShape)
                )
            }

        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun AnimateChildren() {
    var visible by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { visible = visible.not() }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .border(BorderStroke(2.dp, Color.Black))
            .padding(2.dp)
            .size(80.dp)
            .clip(RectangleShape),
        propagateMinConstraints = true
    ) {

        // Indirect children specified different animation
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Box(
                Modifier
                    .fillMaxSize()
                    .background(Color(0xFFa4c639))
            ) {
                Image(
                    painter = painterResource(id = R.drawable.jetpack),
                    contentDescription = null,
                    modifier = Modifier.size(80.dp)
                        .clip(CircleShape)
                        .animateEnterExit(
                            enter = slideInVertically(),
                            exit = slideOutVertically()
                        )
                )
            }

        }
    }
}
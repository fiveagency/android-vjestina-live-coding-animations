package five.denisbuketa.animations.ui

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import five.denisbuketa.animations.R
import five.denisbuketa.animations.ui.theme.AndroidColor


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentScreen() {
    LazyColumn(Modifier.padding(start = 16.dp, end = 16.dp)) {
        item { SlideInSlideOutImageContent1() }
        item { SlideInSlideOutImageContent2() }
        item { SlideInSlideOutImageContent3() }
        item { CollapseExpand() }
        item { Spacer(Modifier.height(300.dp)) }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SlideInSlideOutImageContent1() {
    var checked by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { checked = checked.not() }
        ) { Text("Click Me") }
    }

    val startNotChecked = painterResource(R.drawable.ic_baseline_star_outline_24)
    val startChecked = painterResource(R.drawable.ic_baseline_star_rate_24)
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(AndroidColor)
    ) {

        // AnimatedContent
        // TargetState
        // transitionSpec and with keyword
        // two states can be imagined as two pieces being animated - two animates that are connected to the state (they can be reversed)
        AnimatedContent(
            targetState = checked,
            transitionSpec = {
                slideInVertically(initialOffsetY = { height -> -height }) with
                        slideOutVertically(targetOffsetY = { height -> height })
            }
        ) { targetChecked ->
            Image(
                painter = if (targetChecked) startChecked else startNotChecked,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SlideInSlideOutImageContent2() {
    var checked by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { checked = checked.not() }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(AndroidColor),
    ) {
        val startNotChecked = painterResource(R.drawable.ic_baseline_star_outline_24)
        val startChecked = painterResource(R.drawable.ic_baseline_star_rate_24)
        AnimatedContent(
            targetState = checked,
            transitionSpec = {
                // This is happening within AnimatedContentScope
                // We can access target state
                if (targetState) {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                } else {
                    slideInVertically { height -> -height } + fadeIn() with
                            slideOutVertically { height -> height } + fadeOut()
                }
            }
        ) { targetChecked ->
            Image(
                painter = if (targetChecked) startChecked else startNotChecked,
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
private fun SlideInSlideOutImageContent3() {
    var count by remember { mutableStateOf(0) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { count++ }
        ) { Text("Click Me") }
    }
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(AndroidColor)
    ) {
        val startNotChecked = painterResource(R.drawable.ic_baseline_star_outline_24)
        val startChecked = painterResource(R.drawable.ic_baseline_star_rate_24)
        AnimatedContent(
            targetState = count,
            transitionSpec = {
                // We have a counter so we can imagine infinite composables being animated, not just two
                slideInVertically(initialOffsetY = { height -> -height }) with
                        slideOutVertically(targetOffsetY = { height -> height })
            }
        ) { targetChecked ->
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = if (targetChecked.mod(2) == 0) startChecked else startNotChecked,
                contentDescription = null
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
private fun CollapseExpand() {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { expanded = !expanded }
        ) { Text("Click Me") }
    }
    Surface(
        color = AndroidColor,
        onClick = { expanded = !expanded }
    ) {
        AnimatedContent(
            targetState = expanded,
            transitionSpec = {
                // Enter exit for the content that causes size change
                fadeIn(animationSpec = tween(150, 150)) with
                        fadeOut(animationSpec = tween(150)) using

                        // SizeTransform for the size
                        SizeTransform { initialSize, targetSize ->
                            // here we can
                            if (targetState) {
                                keyframes {
                                    // At 150millis width should be target size, while height is the same.
                                    // After 150millis, start animate height
                                    IntSize(targetSize.width, initialSize.height) at 150
                                    durationMillis = 300
                                }
                            } else {
                                keyframes {
                                    // At 150millis height should be target size, while width is the same.
                                    // After 150millis, start animate width
                                    IntSize(initialSize.width, targetSize.height) at 150
                                    durationMillis = 300
                                }
                            }
                        }
            }
        ) { targetExpanded ->
            if (targetExpanded) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(8.dp),
                    text = stringResource(R.string.read_more)
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_read_more_24),
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
    }
}
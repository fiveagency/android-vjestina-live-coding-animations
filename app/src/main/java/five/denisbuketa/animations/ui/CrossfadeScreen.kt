package five.denisbuketa.animations.ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import five.denisbuketa.animations.R
import five.denisbuketa.animations.ui.theme.AndroidColor


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CrossfadeScreen() {
    LazyColumn(Modifier.padding(start = 16.dp, end = 16.dp)) {
        item { CrossfadeAnimation() }
    }
}

@OptIn(ExperimentalAnimationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
private fun CrossfadeAnimation() {
    var isStar by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { isStar = isStar.not() }
        ) { Text("Click Me") }
    }
    Box(modifier = Modifier
        .size(80.dp)
        .background(AndroidColor)
    ) {
        Crossfade(targetState = isStar) { isStar ->
            if (isStar) {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_star_rate_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Image(
                    painter = painterResource(R.drawable.ic_baseline_read_more_24),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }

        }
    }
}
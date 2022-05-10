package five.denisbuketa.animations.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import five.denisbuketa.animations.R
import five.denisbuketa.animations.ui.theme.AndroidColor


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentSizeScreen() {
    LazyColumn(Modifier.padding(start = 16.dp, end = 16.dp)) {
        item { AnimatedContentSize() }
    }
}

@OptIn(ExperimentalAnimationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
private fun AnimatedContentSize() {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxWidth().padding(top = 24.dp)) {
        Button(
            modifier = Modifier.align(Alignment.Center),
            onClick = { expanded = !expanded }
        ) { Text("Click Me") }
    }
    Text(
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp)
            .background(AndroidColor)
            .animateContentSize(),
        text = if (expanded) stringResource(R.string.read_more) else stringResource(R.string.read_more_small)
    )
}
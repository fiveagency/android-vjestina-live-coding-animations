package five.denisbuketa.animations.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun AnimationsPicker(navController: NavHostController) {
    LazyColumn {
        item {
            Text(
                text = "AnimatedVisibility",
                modifier = Modifier
                    .clickable { navController.navigate("screen_1") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            Text(
                text = "AnimatedContent",
                modifier = Modifier
                    .clickable { navController.navigate("screen_2") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            Text(
                text = "AnimatedContentSize",
                modifier = Modifier
                    .clickable { navController.navigate("screen_3") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            Text(
                text = "Crossfade",
                modifier = Modifier
                    .clickable { navController.navigate("screen_4") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            Text(
                text = "AnimateAsState",
                modifier = Modifier
                    .clickable { navController.navigate("screen_5") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            Text(
                text = "Animatable",
                modifier = Modifier
                    .clickable { navController.navigate("screen_6") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            Text(
                text = "UpdateTransition",
                modifier = Modifier
                    .clickable { navController.navigate("screen_7") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
        item {
            Text(
                text = "InfiniteTransition",
                modifier = Modifier
                    .clickable { navController.navigate("screen_8") }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
        }
    }
}
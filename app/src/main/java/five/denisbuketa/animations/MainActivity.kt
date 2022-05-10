package five.denisbuketa.animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import five.denisbuketa.animations.ui.*
import five.denisbuketa.animations.ui.theme.AnimatableScreen
import five.denisbuketa.animations.ui.theme.AnimationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar {
                                Text("Animations")
                            }
                        }
                    ) {
                        val navController = rememberNavController()
                        NavHost(navController, startDestination = "picker") {
                            composable("picker") {
                                AnimationsPicker(navController)
                            }
                            composable("screen_1") {
                                AnimatedVisibilityScreen()
                            }
                            composable("screen_2") {
                                AnimatedContentScreen()
                            }
                            composable("screen_3") {
                                AnimatedContentSizeScreen()
                            }
                            composable("screen_4") {
                                CrossfadeScreen()
                            }
                            composable("screen_5") {
                                AnimateAsStateScreen()
                            }
                            composable("screen_6") {
                                AnimatableScreen()
                            }
                            composable("screen_7") {
                                UpdateTransitionScreen()
                            }
                            composable("screen_8") {
                                InfiniteTransition()
                            }
                        }
                    }
                }
            }
        }
    }
}
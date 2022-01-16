package com.hari.notty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import com.hari.notty.ui.components.BackPressHandler
import com.hari.notty.ui.components.LocalBackPressedDispatcher
import kotlinx.coroutines.launch


@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            ProvideWindowInsets(consumeWindowInsets = true) {
                CompositionLocalProvider(
                    LocalBackPressedDispatcher provides this.onBackPressedDispatcher
                ) {
                    val scaffoldState = rememberScaffoldState()
                    val coroutineScope = rememberCoroutineScope()

                    if (scaffoldState.drawerState.isOpen) {
                        BackPressHandler {
                            coroutineScope.launch {
                                scaffoldState.drawerState.close()
                            }
                        }
                    }

                    NottyApp(
                        scaffoldState,
                        coroutineScope
                    )
                }
            }
        }
    }
}
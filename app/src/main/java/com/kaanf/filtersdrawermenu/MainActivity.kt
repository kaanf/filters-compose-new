package com.kaanf.filtersdrawermenu

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kaanf.filtersdrawermenu.ui.component.RightSidedDrawer
import com.kaanf.filtersdrawermenu.ui.container.DrawerMenu
import com.kaanf.filtersdrawermenu.ui.theme.PentiFiltersDrawerMenuTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var navHostController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PentiFiltersDrawerMenuTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = Color.White) {
                    navHostController = rememberNavController()

                    DrawerMenuComponent(navController = navHostController)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerMenuComponent(navController: NavHostController) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    RightSidedDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerMenu(
                navHostController = navController,
                onCloseClicked = {
                    scope.launch {
                        drawerState.close()
                    }
                })
        }
    ) {
        Scaffold { scaffoldPaddingValues ->
            Column(modifier = Modifier.padding(scaffoldPaddingValues)) {
                Button(onClick = { scope.launch { drawerState.open() } }) {
                    Text(text = "Open")
                }
            }
        }
    }
}
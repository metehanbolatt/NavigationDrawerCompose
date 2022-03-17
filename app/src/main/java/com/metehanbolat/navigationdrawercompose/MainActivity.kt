package com.metehanbolat.navigationdrawercompose

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.metehanbolat.navigationdrawercompose.home.Drawer
import com.metehanbolat.navigationdrawercompose.home.HomeScreen
import com.metehanbolat.navigationdrawercompose.navigation.Screen
import com.metehanbolat.navigationdrawercompose.ui.theme.NavigationDrawerComposeTheme
import com.metehanbolat.navigationdrawercompose.ui.theme.Purple500
import com.metehanbolat.navigationdrawercompose.utils.Constant
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationDrawerComposeTheme {
                NavigationDrawer()
            }
        }
    }
}

@Composable
fun NavigationDrawer() {
    val context = LocalContext.current
    val navController = rememberNavController()
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(
        rememberDrawerState(initialValue = DrawerValue.Closed)
    )
    val topBar: @Composable () -> Unit = {
        TopAppBar(
            title = {
                Text(text = "Metehan Bolat")
            },
            navigationIcon = {
                IconButton(
                    onClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                    Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu Icon")
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        Toast.makeText(context, "Clicked the menu", Toast.LENGTH_SHORT).show()
                    }
                ) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Vertical Icon")
                }
            },
            backgroundColor = Purple500,
            elevation = AppBarDefaults.TopAppBarElevation
        )
    }

    val drawer: @Composable () -> Unit = {
        Drawer { title, route ->
            scope.launch { 
                scaffoldState.drawerState.close()
            }
            Constant.title = title
            Toast.makeText(context, title, Toast.LENGTH_SHORT).show()
            navController.navigate(route = route)
        }
    }

    Scaffold(
        topBar = {
            topBar()
        },
        scaffoldState = scaffoldState,
        drawerContent = { drawer() },
        drawerGesturesEnabled = true
    ) {
        NavigationHost(navController = navController)
    }
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.DrawerScreen.HomeScreen.route
    ) {
        composable(Screen.DrawerScreen.HomeScreen.route) {
            HomeScreen(value = Constant.title)
        }
    }
}
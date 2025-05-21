package com.swaz.notebook.nav

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swaz.notebook.data.AuthView
import com.swaz.notebook.data.NoteView
import com.swaz.notebook.ui.theme.auth.Login
import com.swaz.notebook.ui.theme.auth.Reg
import com.swaz.notebook.ui.theme.screens.Add
import com.swaz.notebook.ui.theme.screens.Lst

@Composable
fun AppNav(
    navController : NavHostController = rememberNavController()
)
{
    NavHost(
        navController = navController,
        startDestination = Route_Lgn
    )
    {
        composable(Route_Lgn) {
            Login(navController)
        }
        composable(Route_Reg) {
            Reg(navController)
        }
    }










}
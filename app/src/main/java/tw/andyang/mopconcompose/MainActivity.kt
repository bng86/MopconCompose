package tw.andyang.mopconcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import tw.andyang.mopconcompose.ui.screen.CreateTodoScreen
import tw.andyang.mopconcompose.ui.screen.EditTodoScreen
import tw.andyang.mopconcompose.ui.screen.TodoListScreen
import tw.andyang.mopconcompose.ui.theme.MopconComposeTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            MopconComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = Route.HOME) {
                        composable(Route.HOME) {
                            TodoListScreen(navController)
                        }
                        composable(Route.CREATE_TODO) {
                            CreateTodoScreen(navController)
                        }
                        composable(
                            Route.EDIT_TODO,
                            arguments = listOf(navArgument("id") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val id = backStackEntry.arguments?.getInt("id")
                                ?: throw IllegalArgumentException("EDIT_TODO should need argument [id]")
                            EditTodoScreen(navController, id)
                        }
                    }
                }
            }
        }
    }
}

object Route {
    const val HOME = "home"
    const val CREATE_TODO = "create_todo"
    const val EDIT_TODO = "edit_todo/{id}"
}
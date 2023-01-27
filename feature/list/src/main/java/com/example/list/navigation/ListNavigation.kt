import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.list.ListScreen

const val listNavigateRoute = "list_route"

fun NavController.navigateToList(navOptions: NavOptions? = null) {
    navigate(listNavigateRoute, navOptions)
}

fun NavGraphBuilder.editScreen() {
    composable(route = listNavigateRoute) {
        ListScreen()
    }
}
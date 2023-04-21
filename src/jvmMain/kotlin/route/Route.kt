package route

import androidx.compose.runtime.*
import page.ChapterPage
import page.HomePage
import route.Route.*

object RouteModel {
    @Composable
    fun NavHome() {
        var mRoute by remember {
            mutableStateOf(Home)
        }

        fun navTo(route: Route) {
            mRoute = route
        }
        when (mRoute) {
            Home -> HomePage()
            Chapter -> ChapterPage()
        }
    }
}

enum class Route {
    Home, Chapter
}
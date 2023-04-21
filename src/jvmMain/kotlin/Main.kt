import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import route.RouteModel


fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "title"
    ) {
        MaterialTheme {
            RouteModel.NavHome()
        }
    }
}

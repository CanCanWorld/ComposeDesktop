package widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChapterItem(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .padding(10.dp)
    )
}
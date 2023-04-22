package page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.Main_Color
import vm.PlayerViewModel

@Composable
fun PlayerPage(
    navToChapter: () -> Unit
) {
    val vm = PlayerViewModel
    var position by remember { mutableStateOf(vm.position) }

    vm.videoData?.let { video ->

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Main_Color)
                .padding(horizontal = 20.dp, vertical = 10.dp),
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = "返回",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(30.dp)
                    .clickable {
                        navToChapter()
                    },
                tint = Color.White
            )

            Text(
                text = video.title + "-" + vm.chapters[position].title,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(Alignment.Center),
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }
    }

}
package widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lt.load_the_image.rememberImagePainter
import entity.VideoData
import theme.Main_Color
import utils.ClutterUtils.encodeOnlyChinese
import vm.HomeViewModel

@Composable
fun VideoItem(
    videoData: VideoData,
    vm: HomeViewModel,
    navToChapter: () -> Unit
) {

    Column(
        modifier = Modifier
            .padding(10.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .clickable { vm.click(videoData) { navToChapter() } },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        val url = encodeOnlyChinese(videoData.cover, Charsets.UTF_8)
        Image(
            painter = rememberImagePainter(url),
            contentDescription = videoData.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .weight(1f)
        )


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Main_Color)
                .padding(10.dp),
        ) {
            Text(
                videoData.title,
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.Center)
                    .background(Main_Color)
            )
        }
    }
}
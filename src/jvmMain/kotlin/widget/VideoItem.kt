package widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lt.load_the_image.rememberImagePainter
import entity.VideoData
import utils.ClutterUtils.encodeOnlyChinese
import vm.HomeViewModel

@Composable
fun VideoItem(videoData: VideoData, vm: HomeViewModel) {

    Column(
        modifier = Modifier
            .clickable { vm.click(videoData) }
    ) {
        val url = encodeOnlyChinese(videoData.cover, Charsets.UTF_8)
        println("url: $url")
        Image(
            painter = rememberImagePainter(url),
            contentDescription = videoData.title,
        )
        Text(videoData.title)
    }
}
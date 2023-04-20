package widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import entity.VideoData
import io.kamel.core.Resource
import io.kamel.core.config.KamelConfig
import io.kamel.core.config.takeFrom
import io.kamel.image.KamelImage
import io.kamel.image.config.Default
import io.kamel.image.config.LocalKamelConfig
import io.kamel.image.config.resourcesFetcher
import io.kamel.image.lazyPainterResource
import java.net.URL

@Composable
fun VideoItem(videoData: VideoData) {
    println(videoData.cover)

    val desktopConfig = KamelConfig {
        takeFrom(KamelConfig.Default)
        // Available only on Desktop.
        resourcesFetcher()
    }
    Column {
//        ImageAsyncImageUrl(
//            url = "https://api.pingcc.cn/video/img/巨蟒1/巨蟒1.jpg",
//            imageCallback = ImageCallback {
//                Image(
//                    modifier = Modifier
//                        .size(40.dp),
//                    painter = it,
//                    contentDescription = videoData.title
//                )
//            }
//        )
        CompositionLocalProvider(LocalKamelConfig provides desktopConfig) {
            when (val resource = lazyPainterResource(data = URL("https://api.pingcc.cn/video/img/100/100.jpg"))) {
                is Resource.Failure -> {

                }

                is Resource.Loading -> {

                }

                is Resource.Success -> {
                    Image(
                        painter = resource.value,
                        contentDescription = ""
                    )
                }
            }
        }
        Text(videoData.title)
    }
}
package page

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowState
import com.lt.load_the_image.rememberImagePainter
import entity.Chapter
import theme.Main_Color
import theme.Main_Bg
import utils.ClutterUtils
import vm.ChapterViewModel
import vm.PlayerViewModel
import widget.ChapterItem
import java.awt.Desktop
import java.net.URI

@Composable
fun ChapterPage(
    windowState: WindowState,
    navToHome: () -> Unit,
    navToPlayer: () -> Unit,
) {

    val vm = ChapterViewModel

    val chapters by remember {
        mutableStateOf(mutableStateListOf<Chapter>())
    }

    vm.loadChapter() {
        chapters.clear()
        chapters.addAll(it)
    }
    vm.videoData?.let { video ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Main_Bg),
        ) {

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
                            navToHome()
                        },
                    tint = Color.White
                )

                Text(
                    text = video.title,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.Center),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Row(
                modifier = Modifier
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .height(200.dp)
            ) {
                val url = ClutterUtils.encodeOnlyChinese(video.cover, Charsets.UTF_8)
                Image(
                    painter = rememberImagePainter(url),
                    contentDescription = video.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(200.dp)
                )
                Spacer(modifier = Modifier.width(20.dp))
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    item {
                        Text(
                            "简述：" + video.descs.trimStart(),
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
                    item {
                        Text(
                            "导演：" + video.director,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
                    item {
                        Text(
                            "演员：" + video.actor,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
                    item {
                        Text(
                            "地区：" + video.region,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                    }
                    item {
                        Text("发布时间：" + video.releaseTime)
                    }
                }
            }
            val count = if (windowState.size.width.value.toInt() / 160 == 0) 1 else windowState.size.width.value.toInt() / 160
            LazyVerticalGrid(
                columns = GridCells.Fixed(count),
                modifier = Modifier
                    .padding(horizontal = 10.dp)
            ) {
                items(chapters.size) {
                    ChapterItem(chapters[it].title) {
                        PlayerViewModel.videoData = video
                        PlayerViewModel.chapters.clear()
                        PlayerViewModel.chapters.addAll(chapters)
                        PlayerViewModel.position = it
                        navToPlayer()
                    }
                }
            }
        }
    }
}
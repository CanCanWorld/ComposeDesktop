package page

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.WindowState
import theme.Main_Color
import theme.Main_Bg
import vm.HomeViewModel
import widget.CustomEdit
import widget.VideoItem

@Composable
fun HomePage(
    windowState: WindowState,
    navToChapter: () -> Unit
) {
    val vm = HomeViewModel
    var keyword by remember { mutableStateOf("") }
    val videos by remember { mutableStateOf(vm.videos) }
    var error by remember { mutableStateOf(false) }
    val scrollState = rememberLazyGridState()
    var isLoading by remember { mutableStateOf(false) }
    var page = 1

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Main_Color)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            CustomEdit(
                text = keyword,
                onValueChange = {
                    keyword = it
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, top = 20.dp, end = 16.dp)
                    .height(50.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Main_Bg, shape = MaterialTheme.shapes.medium)
                    .padding(horizontal = 16.dp),
                hint = "请输入关键字",
                iconSpacing = 16.dp,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                isError = error
            )
            Spacer(Modifier.width(20.dp))
            Text(
                "搜索",
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        error = keyword == ""
                        if (!error) {
                            page = 0
                            vm.search(keyword, page) {
                                videos.clear()
                                videos.addAll(it)
                            }
                        }
                    }
                    .background(Color.White.copy(0.1f))
                    .padding(15.dp),
            )
        }
        Box(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 20.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(Main_Bg)
                .weight(1f),
        ) {
            val count =
                if (windowState.size.width.value.toInt() / 160 == 0) 1 else windowState.size.width.value.toInt() / 160
            if (!scrollState.isScrollInProgress&& scrollState.canScrollBackward && !scrollState.canScrollForward) {
                isLoading = true
                println("loading")
                page++
                vm.search(keyword, page) {
                    isLoading = false
                    videos.addAll(it)
                }
            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(count),
                state = scrollState,
            ) {
                items(videos.size) {
                    VideoItem(videos[it], vm, navToChapter)
                }
            }
        }

        if (isLoading) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    color = Main_Bg,
                    modifier = Modifier.size(30.dp)
                )
                Spacer(Modifier.width(10.dp))
                Text("加载中", color = Main_Bg,
                    fontWeight = FontWeight.Bold
                )
            }
        } else {
            Spacer(Modifier.height(40.dp))
        }
    }
}



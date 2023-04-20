package page

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import entity.VideoData
import vm.HomeViewModel
import widget.VideoItem

@Composable
fun HomePage() {
    val vm = HomeViewModel()
    var keyword by remember { mutableStateOf("") }
    var videos by remember { mutableStateOf(mutableStateListOf<VideoData>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = keyword,
                onValueChange = {
                    keyword = it
                },
                maxLines = 1,
                label = {
                    Text("请输入关键字")
                }
            )

            Spacer(Modifier.width(20.dp))
            Button(
                onClick = {
                    vm.search(keyword) {
                        println(it)
                        videos.clear()
                        videos.addAll(it)
                    }
                },
            ) {
                Text(
                    "搜索",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        }
        Text(keyword)
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            items(videos.size) {
                VideoItem(videos[it])
            }
        }
    }
}



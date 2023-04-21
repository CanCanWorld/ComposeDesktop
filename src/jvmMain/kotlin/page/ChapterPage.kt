package page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import entity.Chapter
import vm.ChapterViewModel
import widget.ChapterItem

@Composable
fun ChapterPage() {

    val vm = ChapterViewModel

    val chapters by remember {
        mutableStateOf(mutableStateListOf<Chapter>())
    }

    vm.loadChapter() {
        chapters.clear()
        chapters.addAll(it)
    }
    vm.videoData?.let { video ->

        Column {
            Row {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                ) {
                    Text(video.title)
                    Text(video.descs)
                    Text(video.director)
                    Text(video.actor)
                    Text(video.region)
                    Text(video.releaseTime)
                }
            }
            LazyVerticalGrid(columns = GridCells.Fixed(6)) {
                items(chapters.size) {
                    ChapterItem(chapters[it].title)
                }
            }
        }
    }
}
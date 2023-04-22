package vm

import entity.Chapter
import entity.VideoData

object PlayerViewModel {
    var videoData: VideoData? = null

    val chapters = mutableListOf<Chapter>()

    var position = 0
}
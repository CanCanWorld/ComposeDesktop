package vm

import entity.Chapter
import entity.VideoChapter
import entity.VideoData
import http.ApiService
import http.ProvideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object ChapterViewModel {

    var videoData: VideoData? = null

    fun loadChapter(callback: (List<Chapter>) -> Unit) {
        videoData?.let {
            val apiService = ProvideRetrofit.retrofit.create(ApiService::class.java);
            val call = apiService.getVideoChapter(it.videoId)
            call.enqueue(object : Callback<VideoChapter> {
                override fun onResponse(call: Call<VideoChapter>, response: Response<VideoChapter>) {
                    response.body()?.data?.chapterList?.let { chapterList ->
                        callback(chapterList)
                    }
                }

                override fun onFailure(call: Call<VideoChapter>, t: Throwable) {
                }
            })
        }
    }
}
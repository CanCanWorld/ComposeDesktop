package vm

import androidx.compose.runtime.mutableStateListOf
import entity.Video
import entity.VideoData
import http.ApiService
import http.ProvideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object HomeViewModel {

    val videos = mutableStateListOf<VideoData>()

    fun search(keyword: String, page: Int, update: (List<VideoData>) -> Unit) {
        println(keyword)
        val apiService = ProvideRetrofit.retrofit.create(ApiService::class.java);
        val call = apiService.getVideo(keyword, page, 30)
        call.enqueue(object : Callback<Video> {
            override fun onResponse(call: Call<Video>, response: Response<Video>) {
                response.body()?.data?.let { v ->
                    update(v)
                }
            }

            override fun onFailure(call: Call<Video>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun click(videoData: VideoData, navToChapter: () -> Unit) {
        ChapterViewModel.videoData = videoData
        navToChapter()
    }
}
package vm

import entity.Video
import entity.VideoData
import http.ApiService
import http.ProvideRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import route.Route
import route.RouteModel

object HomeViewModel {

    fun search(keyword: String, callback: (List<VideoData>) -> Unit) {
        println(keyword)
        val apiService = ProvideRetrofit.retrofit.create(ApiService::class.java);
        val call = apiService.getVideo(keyword, 1, 30)
        call.enqueue(object : Callback<Video> {
            override fun onResponse(call: Call<Video>, response: Response<Video>) {
                response.body()?.data?.let { videos ->
                    callback(videos)
                }
            }

            override fun onFailure(call: Call<Video>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }

    fun click(videoData: VideoData) {
        RouteModel.NavHome()
        ChapterViewModel.videoData = videoData
    }
}
package vm

import entity.Video
import entity.VideoData
import http.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.pingcc.cn")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun search(keyword: String, callback: (List<VideoData>) -> Unit) {
        println(keyword)
        val apiService = retrofit.create(ApiService::class.java);
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
}
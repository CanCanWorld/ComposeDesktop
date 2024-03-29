package http

import entity.Video
import entity.VideoChapter
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("/video/search/title/{title}/{page}/{limit}")
    fun getVideo(@Path("title") title: String,@Path("page") page: Int,@Path("limit") limit: Int) : Call<Video>

    @GET("/videoChapter/search/{vid}")
    fun getVideoChapter(@Path("vid") vid: String) :Call<VideoChapter>
}
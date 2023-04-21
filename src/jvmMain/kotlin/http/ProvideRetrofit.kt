package http

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ProvideRetrofit {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.pingcc.cn")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
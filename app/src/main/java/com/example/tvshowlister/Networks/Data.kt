package com.example.tvshowlister.Networks

import com.example.tvshowlister.Model.ImdbResponse
import io.reactivex.rxjava3.core.Flowable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Data {

    @GET("Top250TVs/{apiKey}")
    fun getDataForFragment(@Path("apiKey") apiKey: String): Flowable<Response<ImdbResponse>>
}
package com.example.newsapps.API

import com.example.newsapps.ResponseNews
import com.example.newsapps.fragment.HeadlinesFragment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("v2/top-headlines?country=id&apiKey=b0925d0591c3468c8550be3dcba932c6")
    fun newsHeadline(): Call<ResponseNews>
}
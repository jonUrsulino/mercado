package com.jon.mercado.model

import com.jon.mercado.model.bean.SearchItemsBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IServiceSearch {

    @GET("sites/MLU/search")
    fun getSearchItems(@Query("q") query: String): Call<SearchItemsBean>

}
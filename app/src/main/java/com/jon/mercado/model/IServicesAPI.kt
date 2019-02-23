package com.jon.mercado.model

import com.jon.mercado.model.bean.ItemBean
import com.jon.mercado.model.bean.SearchItemsBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IServicesAPI {

    @GET("sites/MLU/search")
    fun getSearchItems(@Query("q") query: String): Call<SearchItemsBean>

    @GET("items/{itemId}")
    fun getItem(@Path("itemId") itemId: String): Call<ItemBean>

}
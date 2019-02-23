package com.jon.mercado.model

import com.jon.mercado.model.bean.ItemBean
import com.jon.mercado.model.bean.ItemDescriptionBean
import com.jon.mercado.model.bean.SearchItemsBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IServicesAPI {

    @GET("sites/MLB/search")
    fun getSearchItems(@Query("q") query: String): Call<SearchItemsBean>

    @GET("items/{itemId}")
    fun getItem(@Path("itemId") itemId: String): Call<ItemBean>

    @GET("items/{itemId}/description")
    fun getItemDescription(@Path("itemId") itemId: String): Call<ItemDescriptionBean>

}
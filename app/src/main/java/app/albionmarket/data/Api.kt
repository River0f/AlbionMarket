package app.albionmarket.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName
import retrofit2.http.Url

interface Api {
    @GET
    fun getItems(@Url url:String):Call<List<MarketItemPure>>
}
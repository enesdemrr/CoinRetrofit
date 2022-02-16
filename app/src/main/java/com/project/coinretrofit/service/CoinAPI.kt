package com.project.coinretrofit.service

import com.project.coinretrofit.model.CoinModel
import io.reactivex.Observable
import retrofit2.http.GET

interface CoinAPI {
    //https://api2.binance.com/api/v3/ticker/24hr
    @GET("ticker/24hr")
    fun getCoin():Observable<List<CoinModel>>
}
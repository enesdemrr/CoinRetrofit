package com.project.coinretrofit.service

import com.project.coinretrofit.model.CoinModel
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class CoinAPIService {
    private val BASE_URL = "https://api2.binance.com/api/v3/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(CoinAPI::class.java)

    fun getData() : Observable<List<CoinModel>>{
        return api.getCoin()
    }
}
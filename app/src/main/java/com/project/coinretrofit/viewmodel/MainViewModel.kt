package com.project.coinretrofit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.coinretrofit.model.CoinModel
import com.project.coinretrofit.service.CoinAPIService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainViewModel : ViewModel() {

    val coins = MutableLiveData<List<CoinModel>>()
    val coinError = MutableLiveData<Boolean>()
    val coinLoading = MutableLiveData<Boolean>()
    private val coinAPIService = CoinAPIService()
    private val disposable = CompositeDisposable()

    fun refreshData(){
        getDataAPI()
    }

    private fun getDataAPI() {
        disposable.add(
            coinAPIService.getData()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<CoinModel>>(){
                override fun onNext(t: List<CoinModel>) {
                    coins.value = t
                }

                override fun onError(e: Throwable) {
                    coinError.value = true
                    coinLoading.value = false
                    println("HATA $e")

                }

                override fun onComplete() {
                    coinError.value = false
                    coinLoading.value = false
                }

            }))
    }

}
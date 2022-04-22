package com.example.tvshowlister.Presenters

import android.content.Context
import android.util.Log
import com.example.tvshowlister.Model.TvShowDb
import com.example.tvshowlister.Model.TvShowInformationData
import com.example.tvshowlister.Networks.RetrofitRepository
import com.example.tvshowlister.Views.FirstFragmentView
import com.example.tvshowlister.dao.TvShowDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class FirstFragmentPresenter() :
    BasePresenter<FirstFragmentView>() {

    val compositeDisposable = CompositeDisposable()
    var tvShowDao: TvShowDao? = null

    fun setDao(context: Context) {
        tvShowDao = TvShowDb.getDatabase(context).movieDao()
    }

    fun retrofitData() {
        var retroObject = RetrofitRepository()

        var d = retroObject.getData()
            .map {
                //return List<TvShowInformationdata>
                val listTv = mutableListOf<TvShowInformationData>()
                it.forEach { item ->
                    listTv.add(
                        TvShowInformationData(
                            item.title,
                            item.image,
                            item.crew,
                            item.imDbRating
                        )
                    )
                }
                return@map listTv
            }
            .doOnNext {
                //insert to database
                tvShowDao?.insertData(it)
            }
            .flatMap {
                tvShowDao?.getAllData()?.toFlowable()
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                //Log.d(TAG, "retrofitData: Prajwal get data")
                if (it.isNotEmpty()) {
                    view().setRecyclerViewData(it)
                } else {
                    Log.e(TAG, "retrofitData Prajwal: Data is null or empty")
                }
            }, {
                Log.d(TAG, "retrofitData: Prajwal it throwable ${it.message} ")
                it.printStackTrace()
            })
        compositeDisposable.add(d)
    }

    fun CompositeDisposer() {
        compositeDisposable.dispose()
    }

    companion object {
        const val TAG = "FirstFragmentPresenter"
    }
}


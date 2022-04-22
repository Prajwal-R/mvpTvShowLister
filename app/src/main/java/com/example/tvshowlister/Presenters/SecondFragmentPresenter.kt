package com.example.tvshowlister.Presenters

import android.content.Context
import android.util.Log
import com.example.tvshowlister.Model.TvShowDb
import com.example.tvshowlister.Views.SecondFragmentView
import com.example.tvshowlister.dao.TvShowDao
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class SecondFragmentPresenter: BasePresenter<SecondFragmentView>() {

    val compositeDisposable = CompositeDisposable()
    var tvShowDao: TvShowDao? = null

    fun setDao(context: Context) {
        tvShowDao = TvShowDb.getDatabase(context).movieDao()
    }

    fun getData(id: Int) {
        var d = tvShowDao?.getItemData(id)!!.toFlowable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                       view().setItemData(it)
            },{
                Log.d(TAG, "getData: Prajwal: Something went wrong!")
            })
        compositeDisposable.add(d)
    }

    fun dispose(){
        compositeDisposable.dispose()
    }

    companion object {
        private const val TAG = "Second Presenter"
    }
}
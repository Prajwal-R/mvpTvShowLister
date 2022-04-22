package com.example.tvshowlister.Presenters

import com.example.tvshowlister.Views.BaseView

abstract class BasePresenter<V : BaseView> {

    @Volatile
    lateinit var view: V

    fun bindView(view: V) {
        this.view = view
    }

    fun view(): V = view
}
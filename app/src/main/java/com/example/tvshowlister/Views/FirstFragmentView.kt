package com.example.tvshowlister.Views

import com.example.tvshowlister.Model.TvShowInformationData

interface FirstFragmentView : BaseView {

    fun setRecyclerViewData(tvShowInfo: List<TvShowInformationData>?)
}

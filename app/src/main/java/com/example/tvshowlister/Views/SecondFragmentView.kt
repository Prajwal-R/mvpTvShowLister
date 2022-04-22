package com.example.tvshowlister.Views

import com.example.tvshowlister.Model.TvShowInformationData

interface SecondFragmentView: BaseView {

    fun setItemData(itemData: TvShowInformationData)
}
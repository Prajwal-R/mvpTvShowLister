package com.example.tvshowlister.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.tvshowlister.Model.TvShowInformationData
import io.reactivex.rxjava3.core.Single

@Dao
interface TvShowDao {

    @Insert
    fun insertData(data: List<TvShowInformationData>)

    @Query("SELECT * FROM movie_details")
    fun getAllData(): Single<List<TvShowInformationData>>

    @Query("SELECT * FROM movie_details WHERE id IS :itemId")
    fun getItemData(itemId: Int): Single<TvShowInformationData>
}
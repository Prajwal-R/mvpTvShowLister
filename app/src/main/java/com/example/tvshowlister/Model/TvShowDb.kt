package com.example.tvshowlister.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.tvshowlister.dao.TvShowDao

@Database(entities = [TvShowInformationData::class], version = 1, exportSchema = false)
abstract class TvShowDb : RoomDatabase() {

    abstract fun movieDao(): TvShowDao

    companion object {
        @Volatile
        private var instance: TvShowDb? = null

        @JvmStatic
        fun getDatabase(context: Context): TvShowDb {
            var INSTANCE = instance
            if (INSTANCE != null) {
                return INSTANCE
            }

            synchronized(this) {
                val databaseInstance = databaseBuilder(
                    context.applicationContext,
                    TvShowDb::class.java,
                    "movie_details"
                ).build()
                //instance = databaseInstance
                return databaseInstance
            }
        }
    }
}
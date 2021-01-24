package com.bekci.tasteofcinema

import android.content.Context
import androidx.room.*
import com.bekci.tasteofcinema.contracts.FilmDAO
import com.bekci.tasteofcinema.model.FilmDBO

@Database(entities = [FilmDBO::class], exportSchema=false, version=1)
public abstract class FilmDatabaseHelper : RoomDatabase() {
    abstract fun filmDao(): FilmDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: FilmDatabaseHelper? = null

        fun getDatabase(context: Context): FilmDatabaseHelper {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabaseHelper::class.java,
                    "film_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}

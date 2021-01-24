package com.bekci.tasteofcinema

import android.app.Application
import com.bekci.tasteofcinema.util.DatabaseRepository

class TasteOfApplication : Application() {

    val database by lazy {FilmDatabaseHelper.getDatabase(this)}
    val repository by lazy { DatabaseRepository(database.filmDao())}
}
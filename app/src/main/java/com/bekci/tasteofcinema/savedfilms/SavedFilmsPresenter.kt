package com.bekci.tasteofcinema.savedfilms

import android.content.Context
import android.os.AsyncTask
import com.bekci.tasteofcinema.FilmDatabaseHelper
import com.bekci.tasteofcinema.model.FilmDBO

class SavedFilmsPresenter(conView : SavedFilmContract.View) : SavedFilmContract.Presenter {

    private var view = conView

    override fun fetchFilms(context : Context){
        AsyncTask.execute {
            val savedFilms = FilmDatabaseHelper.getDatabase(context).filmDao().getAllFilms()
            view.onFilmsFetched(savedFilms)
        }

    }

    override fun deleteSavedFilm(context : Context, filmDBO: FilmDBO, itemPos: Int) {
        AsyncTask.execute {
            FilmDatabaseHelper.getDatabase(context).filmDao().deleteFilm(filmDBO)
            view.onFilmsDeleted(itemPos)
        }
    }

    override fun onStart() {

    }

    override fun onDestroy() {

    }
}
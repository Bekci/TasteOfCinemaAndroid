package com.bekci.tasteofcinema.savedfilms

import android.content.Context
import com.bekci.tasteofcinema.core.BasePresenter
import com.bekci.tasteofcinema.core.BaseView
import com.bekci.tasteofcinema.model.FilmDBO

interface SavedFilmContract {

    interface Presenter : BasePresenter {
        fun fetchFilms(context : Context)
        fun deleteSavedFilm(context : Context, filmDBO: FilmDBO, itemPos: Int)
    }

    interface View : BaseView<Presenter> {
        fun onFilmsFetched(savedFilms : List<FilmDBO>)
        fun onFilmsDeleted(itemPos: Int)
    }

}
package com.bekci.tasteofcinema.singlefilm

import android.content.Context
import com.bekci.tasteofcinema.core.BasePresenter
import com.bekci.tasteofcinema.core.BaseView
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.OmdbFullFilm

interface SingleFilmContract {

    interface View : BaseView<SingleFilmContract.Presenter>{
        fun filmExists()
        fun filmAdded()
    }

    interface Presenter : BasePresenter {
        fun cleanFilmTitle(film : Film?): String?
        fun saveFilmDatabase(selectedFilm : OmdbFullFilm, filmDetail : String?, context: Context)
    }
}
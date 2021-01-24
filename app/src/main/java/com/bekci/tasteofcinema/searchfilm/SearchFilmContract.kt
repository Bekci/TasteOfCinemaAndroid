package com.bekci.tasteofcinema.searchfilm

import android.content.Context
import com.bekci.tasteofcinema.core.BasePresenter
import com.bekci.tasteofcinema.core.BaseView
import com.bekci.tasteofcinema.model.OmdbFilm
import com.bekci.tasteofcinema.model.OmdbFullFilm
import com.bekci.tasteofcinema.model.OmdbSearchResult

interface SearchFilmContract {

    interface Presenter : BasePresenter {
        fun searchFilms(context : Context, searchStr : String)
        fun fetchFilm(context: Context, filmId: String)
    }

    interface View : BaseView<Presenter> {
        fun onFilmsFetched(savedFilms : List<OmdbFilm?>)
        fun onFilmFetched(film : OmdbFullFilm)
    }

}
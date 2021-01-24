package com.bekci.tasteofcinema.contracts

import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.FilmDBO

interface SavedFilmRecyclerClickInterface {
    fun onItemClicked(selectedFilm : FilmDBO)
    fun onItemLongClicked(selectedFilm: FilmDBO, pos: Int) : Boolean
}
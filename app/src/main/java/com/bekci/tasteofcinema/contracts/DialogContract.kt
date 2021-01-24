package com.bekci.tasteofcinema.contracts

import com.bekci.tasteofcinema.model.OmdbFullFilm

interface DialogContract {
    fun passSelectedFilm(selectedFilm : OmdbFullFilm)
}
package com.bekci.tasteofcinema.contracts

import com.bekci.tasteofcinema.model.OmdbFilm

interface DialogRecyclerInterface {
    fun onDialogRecyclerItemClicked(clickedItem : OmdbFilm)
}
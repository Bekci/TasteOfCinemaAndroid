package com.bekci.tasteofcinema.contracts

import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.ListContent
import com.bekci.tasteofcinema.model.ListMainInfo

interface ParserInterface {
    fun onListsParsed(listLists : List<ListMainInfo>)

    fun onListContentParsed(listContent: ListContent)

    fun onListPageParsed(listFilms : List<Film>)

    fun onRequestFailed()
}
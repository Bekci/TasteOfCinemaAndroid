package com.bekci.tasteofcinema.`interface`

import com.bekci.tasteofcinema.model.ListMainInfo

interface ParserInterface {
    fun onListsParsed(listLists : List<ListMainInfo>)
}
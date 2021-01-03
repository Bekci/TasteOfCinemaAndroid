package com.bekci.tasteofcinema.listmain

import com.bekci.tasteofcinema.`interface`.ParserInterface
import com.bekci.tasteofcinema.`interface`.TaskInterface
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.ListContent
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.util.WebSiteParser

class ListMainPresenter(view : ListMainContract.View) : ListMainContract.Presenter, ParserInterface {

    private var view : ListMainContract.View? = view

    override fun fetchListContent(listMainInfo: ListMainInfo) {
        WebSiteParser.parseListContent(listMainInfo, this)
    }

    override fun onStart() {}

    override fun onDestroy() {}

    override fun onListsParsed(listLists: List<ListMainInfo>) {}

    override fun onListContentParsed(listContent: ListContent) {
        view?.onListContentFetched(listContent)
    }

    override fun onListPageParsed(listFilms: List<Film>) {}

}
package com.bekci.tasteofcinema.home

import com.bekci.tasteofcinema.`interface`.ParserInterface
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.ListContent
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.util.WebSiteParser

class HomePagePresenter(view: HomePageContract.View) : HomePageContract.Presenter, ParserInterface {

    private var view: HomePageContract.View? = view
    private var currentListPage = 1

    override fun fetchLists() {
        WebSiteParser.parseLists(currentListPage, this)
    }

    override fun resetNumFetchedPages() {
        currentListPage = 1
    }

    override fun onStart() {

    }

    override fun onDestroy() {

    }

    override fun onListsParsed(listLists: List<ListMainInfo>) {
        view?.onListFetched(listLists)
        currentListPage += 1
    }

    override fun onListContentParsed(listContent: ListContent) {

    }

    override fun onListPageParsed(listFilms: List<Film>) {}

}
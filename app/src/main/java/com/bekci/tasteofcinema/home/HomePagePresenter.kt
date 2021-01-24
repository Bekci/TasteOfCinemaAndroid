package com.bekci.tasteofcinema.home

import android.util.Log
import com.bekci.tasteofcinema.contracts.ParserInterface
import com.bekci.tasteofcinema.model.Film
import com.bekci.tasteofcinema.model.ListContent
import com.bekci.tasteofcinema.model.ListMainInfo
import com.bekci.tasteofcinema.util.WebSiteParser

class HomePagePresenter(view: HomePageContract.View) : HomePageContract.Presenter, ParserInterface {

    private var view: HomePageContract.View? = view
    private var currentListPage = 1
    private var lastReqTime = 0L

    override fun fetchLists() {
        // Disable consecutive calls
        if(lastReqTime < 1000 || System.currentTimeMillis() - lastReqTime > 2000){
            WebSiteParser.parseLists(currentListPage, this)
            lastReqTime = System.currentTimeMillis()
        }
    }

    override fun resetNumFetchedPages() {
        currentListPage = 1
        lastReqTime = 0L
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

    override fun onRequestFailed() {
        view?.onListCannotFetched()
    }

}
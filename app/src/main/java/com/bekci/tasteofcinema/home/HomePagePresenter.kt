package com.bekci.tasteofcinema.home

class HomePagePresenter(view: HomePageContract.View) : HomePageContract.Presenter {

    private var view: HomePageContract.View? = view
    override fun fetchLists() {
        // TODO:  Fetch list
        view?.onListFetched()
    }

    override fun onStart() {

    }

    override fun onDestroy() {

    }



}
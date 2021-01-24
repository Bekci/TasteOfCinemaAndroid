package com.bekci.tasteofcinema.home

import com.bekci.tasteofcinema.core.BasePresenter
import com.bekci.tasteofcinema.core.BaseView
import com.bekci.tasteofcinema.model.ListMainInfo

interface HomePageContract {

    interface View : BaseView<Presenter> {
        fun onListFetched(listList :List<ListMainInfo>)
        fun onListCannotFetched()
    }

    interface Presenter: BasePresenter {
        fun fetchLists()
        fun resetNumFetchedPages()
    }
}
package com.bekci.tasteofcinema.home

import com.bekci.tasteofcinema.core.BasePresenter
import com.bekci.tasteofcinema.core.BaseView

interface HomePageContract {

    interface View : BaseView<Presenter> {
        fun onListFetched()
    }

    interface Presenter: BasePresenter {

        fun fetchLists()
    }
}
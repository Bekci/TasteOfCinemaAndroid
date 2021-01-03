package com.bekci.tasteofcinema.listmain

import com.bekci.tasteofcinema.core.BasePresenter
import com.bekci.tasteofcinema.core.BaseView
import com.bekci.tasteofcinema.model.ListContent
import com.bekci.tasteofcinema.model.ListMainInfo

interface ListMainContract {
    interface View : BaseView<ListMainContract.Presenter> {
        fun onListContentFetched(listContent: ListContent)
    }

    interface Presenter : BasePresenter{
        fun fetchListContent(listMainInfo : ListMainInfo)
    }
}
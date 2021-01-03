package com.bekci.tasteofcinema.singlefilm

import com.bekci.tasteofcinema.core.BasePresenter
import com.bekci.tasteofcinema.core.BaseView

interface SingleFilmContract {

    interface View : BaseView<SingleFilmContract.Presenter>

    interface Presenter : BasePresenter
}
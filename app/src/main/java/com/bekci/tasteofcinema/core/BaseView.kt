package com.bekci.tasteofcinema.core

interface BaseView<T: BasePresenter> {
    fun setPresenter(presenter: T)
}
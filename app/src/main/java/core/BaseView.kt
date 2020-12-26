package core

interface BaseView<T: BasePresenter> {
    fun setPresenter(presenter: T)
}
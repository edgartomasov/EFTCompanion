package ru.et.eftcompanion.ui.NewsList

import ru.et.eftcompanion.mvp.MvpPresenter

open class NewsListPresenter : MvpPresenter<NewsListView>(){

    open fun getNews() {
        runViewAction(Runnable{ view?.createNewsList() })
    }
}
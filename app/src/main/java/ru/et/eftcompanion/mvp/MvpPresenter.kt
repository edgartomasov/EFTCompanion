package ru.et.eftcompanion.mvp

import android.R
import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.util.LinkedHashSet

open class MvpPresenter<V : MvpView> {

    var view: V? = null
    var isAttached: Boolean = false

    protected var viewActions = LinkedHashSet<Runnable>()
    private val disposables = CompositeDisposable()

    fun attach(view: V) {
        this.view = view
        isAttached = true
        runViewActions()
        onViewAttached()
    }

    fun detach() {
        this.view = null
        isAttached = false
        onViewDetached()
    }

    open fun onViewAttached() {

    }

    open fun onViewDetached() {

    }

    open fun onViewDestroyed() {
        if (disposables != null && !disposables.isDisposed) {
            disposables.dispose()
        }
    }

    fun runViewAction(viewAction: Runnable) {
        if (view != null) {
            viewAction.run()
        } else {
            viewActions.add(viewAction)
        }
    }

    private fun runViewActions() {
        for (viewAction in viewActions) {
            viewAction.run()
        }
        viewActions.clear()
    }

    open inner class MvpObserver<T> : Observer<T> {

        override fun onSubscribe(d: Disposable) {
            disposables.add(d)
        }

        override fun onNext(t: T) {

        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
            view?.onError(e)
        }

        override fun onComplete() {

        }

    }

    open inner class MvpSingleObserver<T> : SingleObserver<T> {

        override fun onSubscribe(d: Disposable) {
            disposables.add(d)
        }

        override fun onSuccess(t: T) {

        }

        override fun onError(e: Throwable) {
            e.printStackTrace()
            view?.onError(e)
        }
    }
}
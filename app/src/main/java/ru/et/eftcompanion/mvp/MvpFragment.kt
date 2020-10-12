package ru.et.eftcompanion.mvp

import android.R
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import ru.et.eftcompanion.App
import ru.et.eftcompanion.di.ApplicationComponent
import javax.inject.Inject

abstract class MvpFragment<V: MvpView, P : MvpPresenter<V>> : Fragment(), MvpView {
    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject(getApplicationComponent())
        attachPresenter()
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return (context?.applicationContext as App).applicationComponent!!
    }

    private fun attachPresenter() {
        if (!presenter?.isAttached) {
            presenter?.attach(this as V)
        }
    }

    override fun onResume() {
        super.onResume()
        attachPresenter()
    }

    override fun onPause() {
        super.onPause()
        presenter?.detach()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.onViewDestroyed()
    }

    override fun onError(e: Throwable){

    }

    fun hideKeyboard(activity: Activity){
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.findViewById<View>(R.id.content).windowToken, 0)
    }

    fun openKeyboard(activity: Activity){
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }

}
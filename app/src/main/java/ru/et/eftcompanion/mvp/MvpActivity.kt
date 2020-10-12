package ru.et.eftcompanion.mvp

import android.R
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import ru.et.eftcompanion.App
import javax.inject.Inject

abstract class MvpActivity<V : MvpView, P : MvpPresenter<V>> : BaseActivity(), MvpView {
    @Inject
    lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as App).applicationComponent!!)
        attachPresenter()
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onError(error: Throwable) {

    }

    private fun attachPresenter() {
        if (!presenter?.isAttached!!) presenter?.attach(this as V)
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

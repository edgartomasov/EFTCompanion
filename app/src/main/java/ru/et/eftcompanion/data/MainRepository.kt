package ru.et.eftcompanion.data

import androidx.drawerlayout.widget.DrawerLayout
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.*
import ru.et.eftcompanion.model.User
import ru.et.eftcompanion.ui.MainActivity.MainActivity

class MainRepository {

    var mainActivity = MainActivity()

    fun initHomeBtn(boolean: Boolean) {
        if (boolean) {
            mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            mainActivity.toggle?.setDrawerIndicatorEnabled(false)
            mainActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
            mainActivity.supportActionBar?.setDisplayShowHomeEnabled(true)

            mainActivity.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

            mainActivity.toggle?.setToolbarNavigationClickListener {
                mainActivity.navController.popBackStack()
            }
        } else {
            mainActivity.toggle?.setDrawerIndicatorEnabled(true)
            mainActivity.toggle?.setToolbarNavigationClickListener(null)
            mainActivity.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }
}
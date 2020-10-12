package ru.et.eftcompanion.ui.MainActivity

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header_navigation.view.*
import ru.et.eftcompanion.R
import ru.et.eftcompanion.data.MainRepository
import ru.et.eftcompanion.di.ApplicationComponent
import ru.et.eftcompanion.mvp.MvpActivity
import javax.inject.Inject

class MainActivity : MvpActivity<MainView, MainPresenter>(), MainView, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    lateinit var mainRepository: MainRepository

    lateinit var navController: NavController

    var toggle: ActionBarDrawerToggle ?= null

    override fun inject(component: ApplicationComponent) {
        component.inject(this)
        component.inject(presenter)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toggle = ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle!!)
        toggle!!.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        navController = Navigation.findNavController(this,
            R.id.nav_host_fragment
        )
        mainRepository.mainActivity = this
        presenter.checkLogin()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.news -> {
                navController.navigate(R.id.newsListFragment)
            }
            R.id.other -> {
                navController.navigate(R.id.newsListFragment)
            }
            R.id.market -> {
                navController.navigate(R.id.newsListFragment)
            }
        }
        drawer_layout.closeDrawer(GravityCompat.START)
        return super.onOptionsItemSelected(item)
    }

    override fun checkLoginSuccess() {
        nav_view.getHeaderView(0).user_name.text = "User name"
        nav_view.getHeaderView(0).user_exp.text = "Exp or other"
        nav_view.getHeaderView(0).user_btn.text = getString(R.string.exit)
        nav_view.getHeaderView(0).user_btn.setOnClickListener{view ->
            checkLoginError()
        }
        nav_view.menu.findItem(R.id.market).setVisible(true)
    }

    override fun checkLoginError(){
        nav_view.getHeaderView(0).user_name.text = getString(R.string.eft)
        nav_view.getHeaderView(0).user_exp.text = getString(R.string.companion)
        nav_view.getHeaderView(0).user_btn.setOnClickListener{view ->
            navController.navigate(R.id.authFragment)
        }
        nav_view.menu.findItem(R.id.market).setVisible(false)
    }
}

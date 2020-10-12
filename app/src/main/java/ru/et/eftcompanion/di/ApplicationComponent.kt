package ru.et.eftcompanion.di

import dagger.Component
import ru.et.eftcompanion.ui.auth.AuthFragment
import ru.et.eftcompanion.ui.auth.AuthPresenter
import ru.et.eftcompanion.ui.MainActivity.MainActivity
import ru.et.eftcompanion.ui.MainActivity.MainPresenter
import ru.et.eftcompanion.ui.News.NewsFragment
import ru.et.eftcompanion.ui.News.NewsPresenter
import ru.et.eftcompanion.ui.NewsList.NewsListFragment
import ru.et.eftcompanion.ui.NewsList.NewsListPresenter
import ru.et.eftcompanion.ui.SplashScreen.SplashActivity
import ru.et.eftcompanion.ui.SplashScreen.SplashPresenter
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, MvpModule::class))
interface ApplicationComponent {
    fun inject(activity: SplashActivity)
    fun inject(presenter: SplashPresenter)

    fun inject(activity: MainActivity)
    fun inject(presenter: MainPresenter)

    fun inject(fragment: NewsListFragment)
    fun inject(presenter: NewsListPresenter)

    fun inject(fragment: NewsFragment)
    fun inject(presenter: NewsPresenter)

    fun inject(fragment: AuthFragment)
    fun inject(presenter: AuthPresenter)
}
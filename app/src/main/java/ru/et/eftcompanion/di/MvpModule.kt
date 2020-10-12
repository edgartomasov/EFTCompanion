package ru.et.eftcompanion.di

import dagger.Module
import dagger.Provides
import ru.et.eftcompanion.ui.MainActivity.MainPresenter
import ru.et.eftcompanion.ui.News.NewsPresenter
import ru.et.eftcompanion.ui.NewsList.NewsListPresenter
import ru.et.eftcompanion.ui.SplashScreen.SplashPresenter
import ru.et.eftcompanion.ui.auth.AuthPresenter
import ru.et.eftcompanion.util.DateTimeUtil

@Module
class MvpModule {
    @Provides
    fun provideSplashPresenter() = SplashPresenter()

    @Provides
    fun provideMainPresenter() = MainPresenter()

    @Provides
    fun provideNewsListPresenter() = NewsListPresenter()

    @Provides
    fun provideNewsPresenter() = NewsPresenter()

    @Provides
    fun provideAuthPresenter() = AuthPresenter()

    @Provides
    fun provideDateTimeUtil(): DateTimeUtil {
        return DateTimeUtil()
    }
}
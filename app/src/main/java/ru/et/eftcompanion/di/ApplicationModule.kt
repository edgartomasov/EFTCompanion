package ru.et.eftcompanion.di

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import ru.et.eftcompanion.data.MainRepository
import ru.et.eftcompanion.data.NewsRepository
import ru.et.eftcompanion.data.Prefs
import javax.inject.Singleton

@Module
class ApplicationModule(private val context: Context) {

    @Provides
    @Singleton
    fun providePrefs(preferences: SharedPreferences): Prefs {
        return Prefs(preferences)
    }

    @Provides
    fun provideSharedPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(): NewsRepository {
        return NewsRepository()
    }

    @Provides
    @Singleton
    fun provideMainRepository(): MainRepository {
        return MainRepository()
    }
}
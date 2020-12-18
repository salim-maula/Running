package com.example.running.di

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.room.Room
import com.example.running.db.RunningDatabase
import com.example.running.other.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.running.other.Constants.KEY_NAME
import com.example.running.other.Constants.KEY_WEIGHT
import com.example.running.other.Constants.RUNNING_DATABASE_NAME
import com.example.running.other.Constants.SHARED_PREFERENCE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

//Class sering kali memerlukan referensi ke class lain. Misalnya,
// class Car mungkin memerlukan referensi ke class Engine. Class wajib ini disebut dependensi,
// dan dalam contoh ini class Car bergantung pada sebuah instance dari class Engine untuk dijalankan.

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    //Anda dapat memberi tahu Hilt cara menyediakan instance
    // jenis ini dengan cara membuat fungsi di dalam modul Hilt dan menganotasi fungsi itu dengan @Provides.
    @Provides
    fun provideRunnningDatabase(
            //Hilt menyediakan beberapa penentu yang telah ditetapkan. Misalnya,
            // karena Anda mungkin memerlukan class Context dari aplikasi atau aktivitas,
            // Hilt menyediakan penentu @ApplicationContext dan @ActivityContext.
            @ApplicationContext app: Context
    ) = Room.databaseBuilder(
            app,
            RunningDatabase::class.java,
            RUNNING_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRunDao(db:RunningDatabase)= db.getRunDao()

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app: Context) =
            app.getSharedPreferences(SHARED_PREFERENCE_NAME, MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideName(sharedPref: SharedPreferences )= sharedPref.getString(KEY_NAME, "") ?:""

    @Singleton
    @Provides
    fun provideWeight(sharedPref: SharedPreferences )= sharedPref.getFloat(KEY_WEIGHT, 80f)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref: SharedPreferences )=
            sharedPref.getBoolean(KEY_FIRST_TIME_TOGGLE, true)
}
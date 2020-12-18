package com.example.running.di

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.running.R
import com.example.running.other.Constants
import com.example.running.repositories.ui.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ServiceComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ServiceScoped


//Modul Hilt adalah class yang dianotasi dengan @Module. Seperti modul Dagger,
// modul ini memberi tahu Hilt cara menyediakan instance jenis tertentu.
// Tidak seperti modul Dagger, Anda harus menganotasikan modul Hilt
// dengan @InstallIn untuk memberi tahu Hilt class Android mana yang akan digunakan atau dipasang dalam setiap modul.
@Module
@InstallIn(ServiceComponent::class)
object ServiceModule {

    @ServiceScoped
    @Provides
    fun provideFusedLocationProviderClient(
            @ApplicationContext app: Context
    ) = FusedLocationProviderClient(app)

    @ServiceScoped
    @Provides
    fun provideMainActivityPendingIntent(
            @ApplicationContext app:Context
    )= PendingIntent.getActivity(
            app,
            0,
            Intent(app, MainActivity::class.java).also {
                it.action = Constants.ACTION_SHOW_TRACKING_FRAGMENT
            },
            PendingIntent.FLAG_UPDATE_CURRENT
    )

    @ServiceScoped
    @Provides
    fun provideBaseNotificationBuilder(
            @ApplicationContext app: Context,
            pendingIntent: PendingIntent
    )=  NotificationCompat.Builder(app, Constants.NOTIFICATION_CHANNEL_ID)
            .setAutoCancel(false)
            .setOngoing(true)
            .setSmallIcon(R.drawable.ic_directions_run_black_24dp)
            .setContentTitle("Running App")
            .setContentTitle("00:00:00")
            .setContentIntent(pendingIntent)

}
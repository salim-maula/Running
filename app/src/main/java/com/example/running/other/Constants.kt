package com.example.running.other

import android.graphics.Color

object Constants {


    //consts adalah konstanta waktu kompilasi. Berarti bahwa nilai mereka harus ditetapkan selama waktu kompilasi,
    // tidak seperti vals, di mana itu dapat dilakukan saat runtime.

    //Ini berarti, bahwa consts tidak pernah dapat ditugaskan ke fungsi atau konstruktor kelas,
    // tetapi hanya untuk Stringatau primitif.

    const val RUNNING_DATABASE_NAME = "running_db"

    const val  REQUST_CODE_LOCATION_PERMISSSION = 0

    const val  ACTION_START_OR_RESUME_SERVICE = " ACTION_START_OR_RESUME_SERVICE"
    const val  ACTION_PAUSE_SERVICE = " ACTION_PAUSE_SERVICE"
    const val  ACTION_STOP_SERVICE = " ACTION_STOP_SERVICE "
    const val  ACTION_SHOW_TRACKING_FRAGMENT = " ACTION_SHOW_TRACKING_FRAGMENT "

    const val TIMER_UPDATE_INTERVAL = 50L

    const val SHARED_PREFERENCE_NAME = "sharedPref"
    const val KEY_FIRST_TIME_TOGGLE = "KEY_FIRST_TIME_TOGGLE"
    const val KEY_NAME = "KEY_NAME"
    const val KEY_WEIGHT = "KEY_WEIGHT"

    const val LOCATION_UPDATE_INTERVAL = 5000L
    const val FASTEST_LOCATION_INTERVAL = 2000L

    const val POLYLINE_COLOR = Color.RED
    const val POLYLINE_WIDTH = 8f
    const val MAP_ZOOM = 15f

    const val NOTIFICATION_CHANNEL_ID = "tracking_channel"
    const val NOTIFICATION_CHANNEL_NAME = "Tracking"
    const val NOTIFICATION_ID = 1
}
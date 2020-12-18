package com.example.running.db

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity adalah ibaratnya sebuah tabel
// yang merepresentasikan object di dunia nyata. Pada tutorial CRUD ini kita akan memakai entity berupa sebuah barang.
@Entity(tableName = "running_table")
data class Run(
    var img: Bitmap? = null,
    var timestamp: Long = 0L,
    var avgSpeedInKMH: Float = 0f,
    var distanceInMeters: Int = 0,
    var timeInMillis: Long = 0,
    var caloriesBurned: Int = 0
) {
    // id barang (primary key)/ Atribut barang
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
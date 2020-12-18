package com.example.running.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Run::class],
    version = 1
)
//Jika Anda meletakkannya di a Database, semua Tao dan Entitas dalam database itu akan dapat menggunakannya.
// Saat Anda harus menyimpan dalam database beberapa tipe kustom, Anda bisa menggunakan Type Converters.
@TypeConverters(Converters::class)
//Sebuah abstract class adalah kelas yang tidak utuh atau tidak berguna tanpa adanya
// sublass yang konkrit (non-abstract) yang bisa dipakai untuk membuat
// objekClass ini digunakan untuk membuat objek dari Room Database itu sendiri,
// didalamnya terdapat class-class DAO yang akan digunakan.
abstract class RunningDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDAO
}
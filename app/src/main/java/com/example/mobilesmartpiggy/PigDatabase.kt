package com.example.mobilesmartpiggy

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Pigs::class],
    version = 1
)

abstract class PigDatabase: RoomDatabase() {

    abstract val dao: PigsDAO

}
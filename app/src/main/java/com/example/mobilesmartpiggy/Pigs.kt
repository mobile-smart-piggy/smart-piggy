package com.example.mobilesmartpiggy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "itog",
    indices = [
        Index("NG", unique = true)
    ]
)

data class Pigs(
    @PrimaryKey
    @ColumnInfo(name="NG")
    val pigId: Int = 0,
    @ColumnInfo(name="NM")
    val parentFemale: String,
    @ColumnInfo(name="NO")
    val parentMale: String
)

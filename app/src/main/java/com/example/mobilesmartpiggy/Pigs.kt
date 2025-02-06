package com.example.mobilesmartpiggy

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Pigs(
    @PrimaryKey
    val pigId: Int = 0,
    val parentFemale: String,
    val parentMale: String
)

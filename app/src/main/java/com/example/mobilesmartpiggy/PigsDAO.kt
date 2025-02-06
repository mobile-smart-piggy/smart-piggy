package com.example.mobilesmartpiggy

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface PigsDAO {

    @Upsert
    suspend fun upsertPig(pigs: Pigs)

    @Delete
    suspend fun deletePig(pigs: Pigs)

    @Query("SELECT * FROM pigs ORDER BY pigId ASC")
    fun getPigsById(): Flow<List<Pigs>>

    @Query("SELECT * FROM pigs ORDER BY parentFemale ASC")
    fun getPigsByMom(): Flow<List<Pigs>>

    @Query("SELECT * FROM pigs ORDER BY parentMale ASC")
    fun getPigsByDad(): Flow<List<Pigs>>
}
package com.annaginagili.waterapp.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.annaginagili.waterapp.model.Challenge

@Dao
interface ChallengeDao {
    @Query("Select * from Challenge")
    fun getAll(): List<Challenge>

    @Insert
    fun addChallenge(vararg challenge: Challenge)

    @Delete
    fun delete(challenge: Challenge)

    @Query("Delete from Challenge")
    fun deleteAll()

    @Update
    fun edit(challenge: Challenge)
}
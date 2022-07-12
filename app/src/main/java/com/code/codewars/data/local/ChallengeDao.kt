package com.code.codewars.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface ChallengeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertChallenges(list: List<ChallengeEntity>): Completable

    @Query("DELETE FROM challenge_db")
    fun deleteDb(): Completable

    @Query("SELECT * FROM challenge_db")
    fun getChallenges(): Single<List<ChallengeEntity>>
}

package com.code.codewars.di

import android.app.Application
import androidx.room.Room
import com.code.codewars.data.local.ChallengeDao
import com.code.codewars.data.local.ChallengesDb
import com.code.codewars.utils.Common.CHALLENGE_DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Singleton
    @Provides
    internal fun providesChallengesDb(application: Application): ChallengesDb {
        return Room.databaseBuilder(
            application,
            ChallengesDb::class.java,
            CHALLENGE_DB
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    internal fun providesChallengesDao(db: ChallengesDb): ChallengeDao {
        return db.getChallengesDao()
    }
}
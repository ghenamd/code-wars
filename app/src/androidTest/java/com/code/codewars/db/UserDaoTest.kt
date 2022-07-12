package com.code.codewars.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.code.codewars.data.local.ChallengeDao
import com.code.codewars.data.local.ChallengeEntity
import com.code.codewars.data.local.ChallengesDb
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    @get:Rule
    val executionRule = InstantTaskExecutorRule()
    private lateinit var mDatabase: ChallengesDb
    private lateinit var mDao: ChallengeDao

    @Before
    fun setUp() {
        mDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ChallengesDb::class.java
        ).allowMainThreadQueries()
            .build()
        mDao = mDatabase.getChallengesDao()
    }

    @After
    fun tear() {
        mDatabase.close()
    }

    @Test
    fun getAnEmptyListWhenChallengesDbIsEmpty() {
        val result = mDao
            .getChallenges()
            .test()

        result.assertValue(emptyList())
    }

    @Test
    fun insertAListOfChallengeEntityAssertInsertionIsSuccessful() {
        val input = generateListOfEntities()
        mDao.insertChallenges(input).blockingAwait()

        mDao.getChallenges().test()
            .assertValue { list ->
                list[0] == input[0]
            }
    }

    @Test
    fun deleteChallengesDbAssertItsEmpty() {
        val input = generateListOfEntities()
        mDao.insertChallenges(input).blockingAwait()

        mDao.deleteDb().blockingAwait()

        mDao.getChallenges()
            .test()
            .assertValue(emptyList())
    }

    private fun generateListOfEntities(): List<ChallengeEntity> {
        return listOf(
            ChallengeEntity(
                id = "5571d9fc11526780a000011a",
                name = "user",
                description = "description",
                rank = 1,
                rankName = "3 kyu",
                tags = emptyList(),
                languages = emptyList()
            ),
            ChallengeEntity(
                id = "51ba717bb08c1cd60f00002f",
                name = "user",
                description = "description",
                rank = 1,
                rankName = "3 kyu",
                tags = emptyList(),
                languages = emptyList()
            )
        )
    }
}

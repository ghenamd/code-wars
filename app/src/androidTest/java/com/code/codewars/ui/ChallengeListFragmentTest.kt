package com.code.codewars.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.code.codewars.presentation.main.MainActivity
import com.code.codewars.utils.EspressoIdlingResource
import com.code.codewars.R
import com.code.codewars.utils.atPosition
import com.code.codewars.utils.withRecyclerView
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ChallengeListFragmentTest {

    private val validUserName = "g964"
    private val userNameNoChallenges = "ghenamd"

    @Rule
    @JvmField
    var mActivityTestRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun givenAValidUserNameThenCheckRecyclerViewIsPopulated() {
        onView(withId(R.id.home_edt)).perform(replaceText(validUserName), closeSoftKeyboard())
        onView(withId(R.id.home_fetch_btn)).perform(click())

        onView(withRecyclerView(R.id.challenge_list_rv)?.atPositionOnView(1, R.id.name)).check(
            matches(isDisplayed()))
        onView(withRecyclerView(R.id.challenge_list_rv)?.atPositionOnView(1, R.id.description)).check(
            matches(isDisplayed()))
        onView(withRecyclerView(R.id.challenge_list_rv)?.atPositionOnView(1, R.id.rankName)).check(
            matches(isDisplayed()))
    }

    @Test
    fun givenAValidUserNameThenCheckRecyclerViewIsPopulatedWhenGoBackListIsVisible() {
        onView(withId(R.id.home_edt)).perform(replaceText(validUserName), closeSoftKeyboard())
        onView(withId(R.id.home_fetch_btn)).perform(click())

        onView(withId(R.id.challenge_list_rv)).check(matches(atPosition(0, isDisplayed())))

        pressBack()
        onView(withId(R.id.challenge_list_rv)).check(matches(isDisplayed()))
    }

    @Test
    fun givenAValidUserNameWithoutAuthoredChallengesAssertInfoTextIsVisible() {
        onView(withId(R.id.home_edt)).perform(replaceText(userNameNoChallenges), closeSoftKeyboard())
        onView(withId(R.id.home_fetch_btn)).perform(click())

        onView(withId(R.id.challenge_list_info_tv)).check(matches(isDisplayed()))
    }
}

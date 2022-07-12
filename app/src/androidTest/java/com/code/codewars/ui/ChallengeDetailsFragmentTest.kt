package com.code.codewars.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.code.codewars.presentation.list.adapter.ChallengeViewHolder
import com.code.codewars.presentation.main.MainActivity
import com.code.codewars.utils.EspressoIdlingResource
import com.code.codewars.R
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ChallengeDetailsFragmentTest {

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
    fun givenAValidUserNameThenClickOnFirstRvItemAndOpenChallengeDetails() {
        onView(withId(R.id.home_edt)).perform(replaceText("g964"), closeSoftKeyboard())
        onView(withId(R.id.home_fetch_btn)).perform(click())
        onView(withId(R.id.challenge_list_rv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ChallengeViewHolder>(
                1,
                click()
            )
        )
        onView(withId(R.id.challenge_details_description)).check(matches(isDisplayed()))
        onView(withId(R.id.challenge_details_name)).check(matches(isDisplayed()))
    }
}

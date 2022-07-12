package com.code.codewars.utils

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback

class CountingIdlingResource internal constructor(private val resourceName: String) :
    IdlingResource {
    private var resourceCallback: ResourceCallback? = null
    override fun getName(): String {
        return resourceName
    }

    override fun isIdleNow(): Boolean {
        return count == 0
    }

    override fun registerIdleTransitionCallback(callback: ResourceCallback) {
        resourceCallback = callback
    }

    fun increment() {
        count++
    }

    fun decrement() {
        count--
        if (count == 0) {
            if (resourceCallback != null) {
                resourceCallback!!.onTransitionToIdle()
            }
        }
        require(count >= 0) { "Counter corrupted!" }
    }

    companion object {
        private var count = 0
    }
}

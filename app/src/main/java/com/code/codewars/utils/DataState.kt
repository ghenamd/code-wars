package com.code.codewars.utils

sealed class DataState<out T> {

    open operator fun invoke(): T? = null

    class Loading<out T>(
        val cachedData: T? = null
    ) : DataState<T>() {
        override fun equals(other: Any?) = other is Loading<*>

        override fun hashCode() = "Loading".hashCode()

        override fun invoke(): T? = cachedData
    }

    data class Success<out T>(private val data: T) : DataState<T>() {
        override operator fun invoke(): T = data
    }

    data class Error<out T>(
        val formattedError: String?,
        val cachedData: T? = null
    ) : DataState<T>() {
        override fun equals(other: Any?): Boolean {
            if (other !is Error<*>) return false
            return formattedError == other.formattedError
        }

        override fun hashCode(): Int =
            arrayOf(formattedError).contentHashCode()

        override fun invoke(): T? = cachedData
    }

    fun <V> map(mapper: (T?) -> V?): DataState<V> {
        val mapped = mapper(invoke())
        return when (this) {
            is Loading -> Loading(mapped)
            is Success -> {
                requireNotNull(mapped)
                Success(mapped)
            }
            is Error -> Error(formattedError, mapped)
        }
    }
}

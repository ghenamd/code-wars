package com.code.codewars.utils

import com.code.codewars.data.remote.ChallengeDto
import com.code.codewars.data.remote.CodeWarsApi
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object MockCodeWarsApi {

    val mockWebServer = MockWebServer()

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()

    private val retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .baseUrl(mockWebServer.url("/"))
        .build()

    val api: CodeWarsApi = retrofit.create()

    val testBody = """
        {
          "data": [
            {
              "id": "5571d9fc11526780a000011a",
              "name": "The builder of things",
              "description": "For this kata you will be using some meta-programming ...",
              "rank": -3,
              "rankName": "3 kyu",
              "tags": [
                "Algorithms",
                "Metaprogramming",
                "Programming Paradigms",
                "Advanced Language Features",
                "Fundamentals",
                "Domain Specific Languages",
                "Declarative Programming"
              ],
              "languages": ["ruby", "javascript", "python", "coffeescript"]
            },
            {
              "id": "51ba717bb08c1cd60f00002f",
              "name": "Range Extraction",
              "description": "A format for expressing an ordered list of integers ...",
              "rank": -4,
              "rankName": "4 kyu",
              "tags": [
                "Algorithms",
                "String Formatting",
                "Formatting",
                "Logic",
                "Strings"
              ],
              "languages": [
                "javascript",
                "coffeescript",
                "ruby",
                "go",
                "python",
                "java",
                "haskell",
                "csharp",
                "cpp"
              ]
            }
          ]
        }
    """.trimIndent()

    val expectedList = listOf(
        ChallengeDto(
            id = "5571d9fc11526780a000011a",
            name = "The builder of things",
            description = "For this kata you will be using some meta-programming ...",
            rank = -3,
            rankName = "3 kyu",
            tags = listOf(
                "Algorithms",
                "Metaprogramming",
                "Programming Paradigms",
                "Advanced Language Features",
                "Fundamentals",
                "Domain Specific Languages",
                "Declarative Programming"
            ),
            languages = listOf("ruby", "javascript", "python", "coffeescript")
        ),
        ChallengeDto(
            id = "51ba717bb08c1cd60f00002f",
            name = "Range Extraction",
            description = "A format for expressing an ordered list of integers ...",
            rank = -4,
            rankName = "4 kyu",
            tags = listOf(
                "Algorithms",
                "String Formatting",
                "Formatting",
                "Logic",
                "Strings"
            ),
            languages = listOf(
                "javascript",
                "coffeescript",
                "ruby",
                "go",
                "python",
                "java",
                "haskell",
                "csharp",
                "cpp"
            )
        )
    )
}

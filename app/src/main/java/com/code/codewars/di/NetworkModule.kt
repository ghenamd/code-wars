package com.code.codewars.di

import android.app.Application
import com.airbnb.lottie.BuildConfig
import com.code.codewars.R
import com.code.codewars.data.remote.CodeWarsApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import timber.log.Timber
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun providesOkhttpInterceptor(): Interceptor? {
        return if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor { message -> Timber.d(message) }.apply {
                level =
                    if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            }
        } else null
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: Interceptor?
    ): OkHttpClient {

        val okHttpClient = OkHttpClient().newBuilder()
        interceptor?.let { okHttpClient.addInterceptor(it) }

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        httpClient: Lazy<OkHttpClient>,
        gson: Gson,
        context: Application
    ): Retrofit {
        val url = context.getString(R.string.codewars_base_url) + "/"
        return Retrofit.Builder()
            .addCallAdapterFactory(
                RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
            )
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(url)
            .callFactory { request -> httpClient.get().newCall(request) }
            .build()
    }

    @Provides
    @Singleton
    fun provideCodeWarsApi(retrofit: Retrofit): CodeWarsApi {
        return retrofit.create()
    }

    @Provides
    @Named("io")
    fun providesIoScheduler(): Scheduler = Schedulers.io()
}

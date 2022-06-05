package com.example.githubapp.launcher

import android.app.Application
import com.example.githubapp.feature.pulls.model.network.GithubPullApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GitApplication: Application() {
    lateinit var gitPullApiService : GithubPullApi
    lateinit var okHttpClient: OkHttpClient
    lateinit var interceptor: HttpLoggingInterceptor

    companion object {
        var INSTANCE: GitApplication? = null
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initServiceUtil()
        createApiService()
    }

    private fun initServiceUtil() {
        interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    private fun createApiService() {
        gitPullApiService = Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build().create(GithubPullApi::class.java)
    }
}
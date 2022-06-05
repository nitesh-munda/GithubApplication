package com.example.githubapp.feature.pulls.model.network

import com.example.githubapp.feature.pulls.model.network.models.PullRequestData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubPullApi {
    @GET("/repos/{owner}/{repo}/pulls?state=all")
    suspend fun getPullRequests(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ) : Response<List<PullRequestData>>
}
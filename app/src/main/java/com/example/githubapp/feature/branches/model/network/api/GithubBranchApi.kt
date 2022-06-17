package com.example.githubapp.feature.branches.model.network.api

import com.example.githubapp.feature.branches.model.network.model.BranchesDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubBranchApi {

    @Headers("Accept: application/vnd.github.v3+")
    @GET("/repos/{owner}/{repo}/branches")
    suspend fun getBranches(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Response<List<BranchesDataResponse>>
}
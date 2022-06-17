package com.example.githubapp.feature.pulls.model.repo

import com.example.githubapp.feature.pulls.model.network.GithubPullApi
import com.example.githubapp.feature.pulls.model.network.models.PullRequestData

class GitPullRepository(
    private val apiService: GithubPullApi
) {
    suspend fun getPullRequestFromServer(owner: String, repo: String) : Result<List<PullRequestData>> {
        val result = apiService.getPullRequests(
            owner = owner,
            repo = repo
        )

        return if (result.isSuccessful && result.body().isNullOrEmpty().not()) {
            Result.success(result.body()!!)
        } else {
            Result.failure(Throwable(result.errorBody().toString()))
        }
    }
}
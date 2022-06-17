package com.example.githubapp.feature.branches.model.repo

import com.example.githubapp.feature.branches.model.network.api.GithubBranchApi
import com.example.githubapp.feature.branches.model.network.model.BranchesDataResponse

class GithubBranchRepository(
    private val githubBranchApi: GithubBranchApi
) {
    suspend fun getAllBranches(
        owner: String,
        repo: String
    ): Result<List<BranchesDataResponse>> {
        val result = githubBranchApi.getBranches(owner = owner, repo = repo)

        return if (result.isSuccessful && result.body().isNullOrEmpty().not()) {
            Result.success(result.body()!!)
        } else {
            Result.failure(Throwable(result.errorBody().toString()))
        }
    }
}
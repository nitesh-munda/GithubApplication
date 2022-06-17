package com.example.githubapp.feature.branches.model.network.model

data class BranchesDataResponse(
    val name: String,
    val commit: CommitData
)

data class CommitData(
    val sha: String,
    val url: String
)


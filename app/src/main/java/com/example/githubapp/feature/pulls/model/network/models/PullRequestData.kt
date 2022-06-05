package com.example.githubapp.feature.pulls.model.network.models

data class PullRequestData(
    val url: String,
    val state: String,
    val title: String,
    val body: String,
    val user: UserData
)

data class UserData(
    val avatar_url: String,
    val login: String
)
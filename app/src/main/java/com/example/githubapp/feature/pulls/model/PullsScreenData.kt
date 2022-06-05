package com.example.githubapp.feature.pulls.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PullsScreenData(
    val repoName: String,
    val userName: String
) : Parcelable

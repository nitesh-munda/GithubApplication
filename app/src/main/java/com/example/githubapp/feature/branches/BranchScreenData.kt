package com.example.githubapp.feature.branches

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BranchScreenData(
    val owner: String,
    val repository: String
): Parcelable

package com.example.githubapp.feature.branches.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.feature.branches.model.repo.GithubBranchRepository

class BranchViewModelProvider(
    private val repository: GithubBranchRepository,
    private val owner: String,
    private val repo: String
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubBranchesViewModel::class.java)) {
            return GithubBranchesViewModel(
                githubBranchRepository = repository,
                owner = owner,
                repository = repo
            ) as T
        } else {
            throw RuntimeException("View model class cannot be assigned")
        }
    }

}
package com.example.githubapp.feature.pulls.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubapp.feature.pulls.model.repo.GitPullRepository

class PullsViewModelProvider(
    private val repository: GitPullRepository,
    private val owner: String,
    private val repo: String
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PullsViewModel::class.java)) {
            return PullsViewModel(githubRepository = repository, owner = owner, repo = repo) as T
        } else {
            throw RuntimeException("This model is not assignable")
        }
    }
}
package com.example.githubapp.feature.pulls.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.feature.pulls.model.network.models.PullRequestData
import com.example.githubapp.feature.pulls.model.repo.GitPullRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PullsViewModel(
    private val githubRepository: GitPullRepository,
    private val owner: String,
    private val repo: String
) : ViewModel() {
    init {
        launchPage()
    }

    private val pullRequests: MutableLiveData<List<PullRequestData>> = MutableLiveData()
    val exposePullRequestLiveData: LiveData<List<PullRequestData>> = pullRequests

    private fun launchPage() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = githubRepository.getPullRequestFromServer(owner = owner, repo = repo)
            if(result.isSuccess) {
                pullRequests.postValue(result.getOrDefault(emptyList()))
            } else {
                pullRequests.postValue(emptyList())
            }
        }
    }
}
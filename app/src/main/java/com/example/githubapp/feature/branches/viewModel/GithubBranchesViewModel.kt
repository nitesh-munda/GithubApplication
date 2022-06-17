package com.example.githubapp.feature.branches.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubapp.feature.branches.model.network.model.BranchesDataResponse
import com.example.githubapp.feature.branches.model.repo.GithubBranchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GithubBranchesViewModel(
    private val githubBranchRepository: GithubBranchRepository,
    private val owner: String,
    private val repository: String
): ViewModel() {

    init {
        launchPage()
    }

    private val allGitBranches: MutableLiveData<List<BranchesDataResponse>> = MutableLiveData()
    val exposeAllGitBranches: LiveData<List<BranchesDataResponse>> = allGitBranches

    private fun launchPage() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = githubBranchRepository.getAllBranches(owner = owner, repo = repository)
            if (result.isSuccess) {
                allGitBranches.postValue(result.getOrDefault(emptyList()))
            } else {
                allGitBranches.postValue(emptyList())
            }
        }
    }
}
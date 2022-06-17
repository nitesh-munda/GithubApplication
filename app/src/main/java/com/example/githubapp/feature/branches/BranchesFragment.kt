package com.example.githubapp.feature.branches

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.databinding.FragmentBranchBinding
import com.example.githubapp.feature.branches.model.adapter.BranchesRVAdapter
import com.example.githubapp.feature.branches.model.network.model.BranchesDataResponse
import com.example.githubapp.feature.branches.model.repo.GithubBranchRepository
import com.example.githubapp.feature.branches.viewModel.BranchViewModelProvider
import com.example.githubapp.feature.branches.viewModel.GithubBranchesViewModel
import com.example.githubapp.feature.pulls.model.adapter.AdapterCallback
import com.example.githubapp.launcher.GitApplication

class BranchesFragment: Fragment(), AdapterCallback {

    private lateinit var binding: FragmentBranchBinding

    private lateinit var rvAdapter: BranchesRVAdapter<BranchesDataResponse>

    private lateinit var viewModel: GithubBranchesViewModel

    companion object {
        private const val BRANCH_SCREEN_DATA = "SCREEN_DATA"
        fun newInstance(branchScreenData: BranchScreenData): Fragment {
            val bundle = Bundle().apply {
                putParcelable(BRANCH_SCREEN_DATA, branchScreenData)
            }

            return BranchesFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBranchBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupViewModel()
    }

    private fun setupViewModel() {
        val screenData = arguments?.getParcelable<BranchScreenData>(BRANCH_SCREEN_DATA)
        val viewModelProvider = BranchViewModelProvider(
            repository = GithubBranchRepository(
                githubBranchApi = GitApplication.INSTANCE!!.gitBranchApiService
            ),
            owner = screenData?.owner ?: "",
            repo = screenData?.repository ?: ""
        )
        viewModel = viewModelProvider.create(GithubBranchesViewModel::class.java)
        viewModel.exposeAllGitBranches.observe(viewLifecycleOwner, Observer {
            if (it==null){
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_LONG).show()
            } else {
                if (it.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Empty result", Toast.LENGTH_LONG).show()
                }
                rvAdapter.refreshList(it)
            }
        })
    }

    private fun setupViews() {
        rvAdapter = BranchesRVAdapter(this@BranchesFragment)
        binding.rootRv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = rvAdapter
        }
    }

    override fun itemClicked(url: String) {
        Log.v("BranchesFragment", url)
    }
}
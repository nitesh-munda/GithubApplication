package com.example.githubapp.feature.pulls

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapp.databinding.FragmentPullsBinding
import com.example.githubapp.feature.pulls.model.PullsScreenData
import com.example.githubapp.feature.pulls.model.adapter.AdapterCallback
import com.example.githubapp.feature.pulls.model.adapter.PullsRVAdapter
import com.example.githubapp.feature.pulls.model.network.models.PullRequestData
import com.example.githubapp.feature.pulls.model.repo.GitPullRepository
import com.example.githubapp.feature.pulls.viewModel.PullsViewModel
import com.example.githubapp.feature.pulls.viewModel.PullsViewModelProvider
import com.example.githubapp.launcher.GitApplication

class PullsFragment : Fragment(), AdapterCallback {

    private lateinit var pullsBinding: FragmentPullsBinding
    private lateinit var viewModel: PullsViewModel
    private val pullsRVAdapter = PullsRVAdapter<PullRequestData>(this@PullsFragment)

    companion object {
        const val PULLS_SCREEN_DATA = "SCREEN_DATA"
        fun newInstance(screenData: PullsScreenData): Fragment {
            val bundle = Bundle().apply {
                putParcelable(PULLS_SCREEN_DATA, screenData)
            }

            return PullsFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        pullsBinding = FragmentPullsBinding.inflate(inflater)
        return pullsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        val screenData = arguments?.getParcelable<PullsScreenData>(PULLS_SCREEN_DATA)
        viewModel = PullsViewModelProvider(
            repository = GitPullRepository(
                apiService = GitApplication.INSTANCE!!.gitPullApiService
            ),
            owner = screenData?.userName ?: "",
            repo = screenData?.repoName ?: ""
        ).create(PullsViewModel::class.java)
        viewModel.exposePullRequestLiveData.observe(viewLifecycleOwner, Observer {
            if (it==null){
                Toast.makeText(requireContext(), "Something went wrong!", Toast.LENGTH_SHORT).show()
            } else {
                if(it.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Empty result", Toast.LENGTH_SHORT).show()
                }
                pullsRVAdapter.refreshList(it)
            }
        })
    }

    private fun initViews() {
        pullsBinding.rootRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = pullsRVAdapter
        }
    }

    override fun itemClicked(url: String) {
        Log.v("PullsFragment", url)
    }

}
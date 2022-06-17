package com.example.githubapp.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityGitPageBinding
import com.example.githubapp.feature.branches.BranchScreenData
import com.example.githubapp.feature.branches.BranchesFragment
import com.example.githubapp.feature.pulls.PullsFragment
import com.example.githubapp.feature.pulls.model.PullsScreenData
import com.google.android.material.navigation.NavigationBarView

class GitPageActivity: AppCompatActivity(),
    NavigationBarView.OnItemSelectedListener {

    private var repoName: String? = ""
    private var userName: String? = ""

    companion object {
        private const val REPO_NAME_KEY = "REPO_NAME"
        private const val USER_NAME_KEY = "USER_NAME"

        fun startActivity(repoName: String, username: String, context: Context) {
            val bundle = Bundle().apply {
                putString(REPO_NAME_KEY, repoName)
                putString(USER_NAME_KEY, username)
            }
            context.startActivity(Intent(context, GitPageActivity::class.java).putExtras(bundle))
        }
    }

    private lateinit var binding: ActivityGitPageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGitPageBinding.inflate(layoutInflater)
        supportActionBar?.hide()
        setContentView(binding.root)
        repoName = intent.extras?.getString(REPO_NAME_KEY)
        userName = intent.extras?.getString(USER_NAME_KEY)
        setupViews()
        selectFirstByDefault()
    }

    private fun selectFirstByDefault() {
        binding.bottomNavBar.selectedItemId = R.id.first_tab
    }

    private fun setupViews() {
        val userName = intent.extras?.getString(USER_NAME_KEY)
        binding.tvUser.text = "Hi, $userName"
        (binding.bottomNavBar as NavigationBarView).setOnItemSelectedListener(this@GitPageActivity)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.first_tab -> {
                val fragment =
                    PullsFragment.newInstance(
                        PullsScreenData(
                            repoName = repoName ?: "",
                            userName = userName ?: ""
                        )
                    )
                commitFragmentNow(fragment, binding.fragmentContainer.id)
                showCurrentFragment(fragment)
                true
            }

            R.id.second_tab -> {
                val fragment = BranchesFragment.newInstance(
                    BranchScreenData(
                        owner = userName ?: "",
                        repository = repoName ?: ""
                    )
                )
                commitFragmentNow(fragment, binding.fragmentContainer.id)
                showCurrentFragment(fragment)
                true
            }
            else -> {
                false
            }
        }
    }
}
package com.example.githubapp.feature.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapp.R
import com.example.githubapp.databinding.ActivityGitPageBinding
import com.example.githubapp.feature.pulls.PullsFragment
import com.example.githubapp.feature.pulls.model.PullsScreenData
import com.google.android.material.navigation.NavigationBarView

class GitPageActivity: AppCompatActivity(),
    NavigationBarView.OnItemSelectedListener {

    companion object {
        const val FIRST_TAB_TAG = "first"
        const val SECOND_TAB_TAG = "second"
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
                if (findFragmentByTag(FIRST_TAB_TAG) == null) {
                    val repoName = intent.extras?.getString(REPO_NAME_KEY)
                    val userName = intent.extras?.getString(USER_NAME_KEY)
                    val fragment = PullsFragment.newInstance(PullsScreenData(repoName?:"",userName?:""))
                    commitFragmentNow(FIRST_TAB_TAG, fragment, binding.fragmentContainer.id)
                    showCurrentFragment(fragment)
                    true
                } else {
                    val fragment = findFragmentByTag(FIRST_TAB_TAG)
                    showCurrentFragment(fragment!!)
                    true
                }
            }

            R.id.second_tab -> {
                if (findFragmentByTag(SECOND_TAB_TAG) == null) {
                    val fragment = TODO()
                    commitFragmentNow(SECOND_TAB_TAG, fragment, binding.fragmentContainer.id)
                    showCurrentFragment(fragment)
                }
                true
            }
            else -> {
                false
            }
        }
    }
}
package com.example.githubapp.launcher

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.githubapp.databinding.ActivityHomeBinding
import com.example.githubapp.feature.main.GitPageActivity

class HomeActivity : AppCompatActivity() {

    private lateinit var homeBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(homeBinding.root)
        setupViews()
    }

    private fun setupViews() {
        homeBinding.btContinue.setOnClickListener {
            if (homeBinding.edtRepoName.text.toString().isEmpty() || homeBinding.edtUserName.text.toString().isEmpty()) {
                Toast.makeText(this, "Please enter the required fields!", Toast.LENGTH_LONG).show()
            } else {
                launchGitScreen()
            }
        }
    }

    private fun launchGitScreen() {
        GitPageActivity.startActivity(
            repoName = homeBinding.edtRepoName.text.toString(),
            username = homeBinding.edtUserName.text.toString(),
            context = this@HomeActivity
        )
    }

}
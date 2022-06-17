package com.example.githubapp.feature.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.findFragmentByTag(tag: String) : Fragment? {
    return supportFragmentManager.findFragmentByTag(tag)
}

fun AppCompatActivity.commitFragmentNow(fragment: Fragment, container: Int) {
    supportFragmentManager
        .beginTransaction()
        .replace(container, fragment)
        .commitNow()
}

fun AppCompatActivity.showCurrentFragment(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .show(fragment)
        .commitNow()
}
package com.example.githubapp.feature.main

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.findFragmentByTag(tag: String) : Fragment? {
    return supportFragmentManager.findFragmentByTag(tag)
}

fun AppCompatActivity.commitFragmentNow(tag: String, fragment: Fragment, container: Int) {
    supportFragmentManager
        .beginTransaction()
        .add(container, fragment, tag)
        .commitNow()
}

fun AppCompatActivity.showCurrentFragment(fragment: Fragment) {
    supportFragmentManager
        .beginTransaction()
        .show(fragment)
        .commitNow()
}
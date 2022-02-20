package com.example.github_trending_reposotiry.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.github_trending_reposotiry.R
import com.facebook.stetho.Stetho

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
    }
}
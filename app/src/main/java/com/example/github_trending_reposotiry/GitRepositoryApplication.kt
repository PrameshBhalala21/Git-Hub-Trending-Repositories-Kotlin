package com.example.github_trending_reposotiry

import android.app.Application
import androidx.work.*
import com.example.github_trending_reposotiry.db.GitRepositoryDataBase
import com.example.github_trending_reposotiry.network.ApiHelper
import com.example.github_trending_reposotiry.network.ApiServices
import com.example.github_trending_reposotiry.repository.GitRepository
import com.example.github_trending_reposotiry.work.BackGroundWorker
import java.util.concurrent.TimeUnit

class GitRepositoryApplication : Application() {

    lateinit var gitRepository: GitRepository

    override fun onCreate() {
        super.onCreate()
        initialize()
        setupBackgroundWorker()
    }

    private fun setupBackgroundWorker() {
        val constraints =
            Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workerRequest =
            PeriodicWorkRequest.Builder(BackGroundWorker::class.java, 15, TimeUnit.MINUTES)
                .setConstraints(constraints).build()
        WorkManager.getInstance(applicationContext).enqueue(workerRequest)
    }

    private fun initialize() {
        val apiHelpers = ApiHelper.getInstance().create(ApiServices::class.java)
        val database = GitRepositoryDataBase.getInstance(applicationContext)
        gitRepository = GitRepository(apiHelpers, database, applicationContext)
    }
}
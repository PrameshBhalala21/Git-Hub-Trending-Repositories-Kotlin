package com.example.github_trending_reposotiry.work

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.github_trending_reposotiry.GitRepositoryApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BackGroundWorker(private val context: Context, params: WorkerParameters) :
    Worker(context, params) {
    override fun doWork(): Result {
        val repository = (context as GitRepositoryApplication).gitRepository
        CoroutineScope(Dispatchers.IO).launch {
            repository.getGitRepositoryListBackground("language:kotlin")
        }
        return Result.success()
    }
}
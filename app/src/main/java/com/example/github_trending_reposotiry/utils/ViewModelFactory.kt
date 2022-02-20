package com.example.github_trending_reposotiry.utils


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.github_trending_reposotiry.repository.GitRepository
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.viewModel.GitRepositoryListViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val gitRepository: GitRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GitRepositoryListViewModel::class.java)) {
//            return GitRepositoryListViewModel(repository!!, mCon!!,viewLifecycleOwner) as T
            return GitRepositoryListViewModel(gitRepository) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }
}
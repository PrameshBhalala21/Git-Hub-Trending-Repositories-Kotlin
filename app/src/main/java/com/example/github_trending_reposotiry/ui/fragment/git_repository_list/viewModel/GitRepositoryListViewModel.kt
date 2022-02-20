package com.example.github_trending_reposotiry.ui.fragment.git_repository_list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_trending_reposotiry.repository.GitRepository
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.GitHubResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class GitRepositoryListViewModel @Inject constructor(private val gitRepository : GitRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            gitRepository.getGitRepositoryList("language:kotlin")
        }
    }

    val  gitRepositoryAPIResponse : LiveData<GitHubResponseModel>
    get() = gitRepository.response

   /* val  gitRepositoryDatabaseData : LiveData<List<Items>>
    get() = gitRepository.database*/

    val  gitLoaderResponse : LiveData<Boolean>
    get() = gitRepository.loading

    val  gitErrorResponse : LiveData<String>
    get() = gitRepository.error

}
package com.example.github_trending_reposotiry.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.github_trending_reposotiry.db.GitRepositoryDataBase
import com.example.github_trending_reposotiry.db.model.GitRepositoryDataModel
import com.example.github_trending_reposotiry.network.ApiServices
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.GitHubResponseModel
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.Items
import com.example.github_trending_reposotiry.utils.CheckInternetConnections
import javax.inject.Inject

class GitRepository @Inject constructor(
    private val apiServices: ApiServices,
    private val gitRepositoryDataBase: GitRepositoryDataBase,
    private val applicationContext: Context
) {

    private val responseLiveData: MutableLiveData<GitHubResponseModel> = MutableLiveData()
    private val databaseLiveData: MutableLiveData<List<Items>> = MutableLiveData()
    private val loadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private val errorLiveData: MutableLiveData<String> = MutableLiveData()

    val response: LiveData<GitHubResponseModel>
        get() = responseLiveData

    /* val database: LiveData<List<Items>>
         get() = databaseLiveData*/

    val loading: LiveData<Boolean>
        get() = loadingLiveData

    val error: LiveData<String>
        get() = errorLiveData

    suspend fun getGitRepositoryList(query: String) {
        try {
            loadingLiveData.postValue(true)
            if (CheckInternetConnections().checkConnection(applicationContext)) {
                val result = apiServices.getGitRepository(query)
                if (result?.body() != null) {
                    if (result.code() == 200) {
                        /*gitRepositoryDataBase.gitRepositoryDao()
                            .insert(saveDataToDatabaseTable(result.body()!!.items))*/
                        gitRepositoryDataBase.gitRepositoryDao().deleteAllGitRepo()
                        gitRepositoryDataBase.gitRepositoryDao()
                            .insert(result.body()!!.items)
                        responseLiveData.postValue(result.body())
                    } else {
                        errorLiveData.postValue("Something went wrong....!")
                    }
                    loadingLiveData.postValue(false)
                }
            } else {
                loadingLiveData.postValue(true)
                val databaseList = gitRepositoryDataBase.gitRepositoryDao().getAllGitRepoDetails()
                responseLiveData.postValue(GitHubResponseModel(0, false, databaseList))
                loadingLiveData.postValue(false)
            }

        } catch (e: Exception) {
            loadingLiveData.postValue(false)
            errorLiveData.postValue("Something went wrong....!")
            e.printStackTrace()
        }
    }

    suspend fun getGitRepositoryListBackground(query: String) {
        val result = apiServices.getGitRepository(query)
        if (result?.body() != null) {
            if (result.code() == 200) {
                gitRepositoryDataBase.gitRepositoryDao().deleteAllGitRepo()
                gitRepositoryDataBase.gitRepositoryDao()
                    .insert(result.body()!!.items)
            }
        }

    }

    private fun saveDataToDatabaseTable(arrayList: ArrayList<Items>): List<GitRepositoryDataModel> {

        var dataList = ArrayList<GitRepositoryDataModel>()
        var gitRepositoryDataModel: GitRepositoryDataModel

        arrayList.forEachIndexed { index, items ->
            gitRepositoryDataModel = GitRepositoryDataModel(
                0,
                items.owner?.avatarUrl!!,
                items.id!!,
                items.owner?.id!!,
                items.name!!,
                items.fullName!!,
                if (items.description != null) items.description!! else "",
                items.language!!,
                items.stargazersCount!!.toString(),
                items.watchersCount!!.toString(),
                items.forksCount!!.toString(),
                items.openIssuesCount!!.toString(),
                items.createdAt!!,
                items.updatedAt!!,
                items.visibility!!,
                handleTopics(items.topics!!)
            )

            dataList.add(gitRepositoryDataModel)
        }

        return dataList

    }

    private fun handleTopics(topicsList: ArrayList<String>): String {

        var topics = ""

        topicsList.forEachIndexed { index, stringName ->
            topics = if (index == 0) {
                stringName
            } else {
                "$topics, $stringName"
            }
        }

        return topics


    }


}
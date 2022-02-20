package com.example.github_trending_reposotiry.ui.fragment.git_repository_list.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.github_trending_reposotiry.repository.GitRepository
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.GitHubResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GitRepositoryListViewModel(private val gitRepository : GitRepository) : ViewModel() {

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

    /*init {
        disposable = CompositeDisposable()
        cd = mCon.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        databse = GitRepositoryDataBase.getInstance(mCon)
        dataBaseDao = databse.gitRepositoryDao()
    }*/

    /*fun loadGitRepository(stringQuery: String?) {
        loading.value = true
        if (CheckInternetConnections().checkConnection(mCon)) {
            networkRepository.networkService?.getGitRepository(stringQuery!!)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.doOnSubscribe { loading.setValue(true) }
                ?.subscribe({ gitHubResponseModel: GitHubResponseModel? ->
                    this.handleGitRepositoryAPIResponse(
                        gitHubResponseModel!!
                    )
                }) { throwable: Throwable? ->
                    this.handleGitRepositoryAPIError(
                        throwable
                    )

                }?.let {
                    disposable.add(
                        it
                    )
                }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                dataBaseDao.getAllGitRepoDetails().observe(viewLifecycleOwner, { gitHubResponseModel ->  onGitRepositoryResponse(gitHubResponseModel) })
            }

        }
    }
*/
   /* private fun onGitRepositoryResponse(gitHubResponseModel: List<Items>?) {
        databaseLiveData.value = gitHubResponseModel
        loading.value = false

    }

    private fun handleGitRepositoryAPIResponse(jsonObject: GitHubResponseModel) {
        responseLiveData.value = jsonObject
        loading.value = false
    }

    private fun handleGitRepositoryAPIError(throwable: Throwable?) {
        errorValue.value = throwable
        loading.value = false
    }

    fun gitRepositoryResponse(): MutableLiveData<GitHubResponseModel> {
        return responseLiveData
    }

    fun getError(): LiveData<Boolean> {
        return repoLoadError;
    }

    fun getLoading(): LiveData<Boolean> {
        return loading
    }

    fun getThrowable(): LiveData<Throwable> {
        return errorValue
    }*/


}
package com.example.github_trending_reposotiry.network

import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.GitHubResponseModel
import com.google.gson.JsonObject
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    //    @GET("search/repositories?sort=stars")
//    @GET("search/repositories?q={query}")
    @GET("search/repositories")
    suspend fun getGitRepository(
        @Query("q") query: String
    ): Response<GitHubResponseModel>?

}
package com.example.github_trending_reposotiry.di.module

import com.example.github_trending_reposotiry.network.ApiServices
import com.example.github_trending_reposotiry.repository.GitRepository
import dagger.Binds
import dagger.Module

@Module
abstract class ApiServicesModule {

    @Binds
    abstract fun getApiServices(apiServices: ApiServices): ApiServices

}
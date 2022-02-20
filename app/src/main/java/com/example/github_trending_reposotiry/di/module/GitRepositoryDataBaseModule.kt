package com.example.github_trending_reposotiry.di.module

import com.example.github_trending_reposotiry.db.GitRepositoryDataBase
import dagger.Binds
import dagger.Module

@Module
abstract class GitRepositoryDataBaseModule {

    @Binds
    abstract fun getGitRepositoryDatabase(gitRepositoryDataBase: GitRepositoryDataBase) : GitRepositoryDataBase
}
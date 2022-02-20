package com.example.github_trending_reposotiry.di

import com.example.github_trending_reposotiry.di.module.ApiServicesModule
import com.example.github_trending_reposotiry.di.module.ContextModule
import com.example.github_trending_reposotiry.di.module.GitRepositoryDataBaseModule
import com.example.github_trending_reposotiry.ui.activity.MainActivity
import dagger.Component

@Component(
    modules = [ApiServicesModule::class,
        GitRepositoryDataBaseModule::class,
        ContextModule::class,
    ]
)
interface ApplicationComponent {

    fun injectActivity(activity: MainActivity)

}
package com.example.github_trending_reposotiry.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ContextModule {

    @Binds
    abstract fun getApplicationContext(context: Context): Context
}
package com.example.github_trending_reposotiry.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.github_trending_reposotiry.db.dao.GitRepositoryDAO
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.Items
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.OwnerTypeConverter
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.TopicsTypeConverter

@Database(entities = [Items::class], version = 1)
@TypeConverters(TopicsTypeConverter::class, OwnerTypeConverter::class)
abstract class GitRepositoryDataBase : RoomDatabase() {

    abstract fun gitRepositoryDao(): GitRepositoryDAO

    companion object {
        private var instance: GitRepositoryDataBase? = null

        fun getInstance(context: Context): GitRepositoryDataBase {
            if (instance == null) {
                synchronized(this) {
                    instance = Room.databaseBuilder(
                        context, GitRepositoryDataBase::class.java,
                        "gitRepositoryDatabase"
                    ).addTypeConverter(OwnerTypeConverter())
                        .addTypeConverter(TopicsTypeConverter())
                        .build()
                }
            }

            return instance!!

        }

    }
}
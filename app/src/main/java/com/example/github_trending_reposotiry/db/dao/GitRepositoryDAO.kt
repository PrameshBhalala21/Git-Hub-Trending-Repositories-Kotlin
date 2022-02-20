package com.example.github_trending_reposotiry.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.Items

@Dao
interface GitRepositoryDAO {

    @Insert
    fun insert(gitRepositoryDataModel: List<Items>)

    @Query("delete from items")
    fun deleteAllGitRepo()

    @Query("select * from items")
    fun getAllGitRepoDetails(): List<Items>

    /*@Query("select * from git_repo order by watchersCount desc")
    fun getAllGitRepo(): LiveData<List<GitRepositoryDataModel>>

    @Update
    fun update(gitRepositoryDataModel: GitRepositoryDataModel)

    @Delete
    fun delete(gitRepositoryDataModel: GitRepositoryDataModel)
*/
}
package com.example.github_trending_reposotiry.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "git")
data class GitRepositoryDataModel(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userImageIcon: String,
    val itemId: Int,
    val ownerId: Int,
    val userName: String,
    val userFullName: String,
    val description: String,
    val language: String,
    val stargazersCount: String,
    val watchersCount: String,
    val forksCount: String,
    val openIssuesCount: String,
    val createdAt: String,
    val updatedAt: String,
    val visibility: String,
    val topics: String
)
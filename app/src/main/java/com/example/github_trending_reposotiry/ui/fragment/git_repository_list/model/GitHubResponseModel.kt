package com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model

import com.google.gson.annotations.SerializedName

data class GitHubResponseModel(
    @SerializedName("total_count") var totalCount: Int? = null,
    @SerializedName("incomplete_results") var incompleteResults: Boolean? = null,
    @SerializedName("items") var items: List<Items> = arrayListOf()
)
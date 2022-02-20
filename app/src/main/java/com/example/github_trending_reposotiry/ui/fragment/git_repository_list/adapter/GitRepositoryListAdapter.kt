package com.example.github_trending_reposotiry.ui.fragment.git_repository_list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.github_trending_reposotiry.R
import com.example.github_trending_reposotiry.databinding.RowGitRepositoryListBinding
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.Items

class GitRepositoryListAdapter(context: Context, listener: OnClickListener) :
    RecyclerView.Adapter<GitRepositoryListAdapter.MyViewHolder>() {

    private lateinit var binding: RowGitRepositoryListBinding
    private var mCon: Context = context
    private var gitRepoItemList: ArrayList<Items> = ArrayList<Items>()
    private var onClickListener = listener


    @SuppressLint("NotifyDataSetChanged")
    fun addList(gitRepositoryModelList: List<Items>) {
        if (gitRepoItemList != null) {
            clearDataSet()
            gitRepoItemList.addAll(gitRepositoryModelList)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun clearDataSet() {
        if (gitRepoItemList != null) {
            gitRepoItemList.clear()
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        binding = DataBindingUtil.inflate(
            LayoutInflater.from(mCon),
            R.layout.row_git_repository_list,
            parent,
            false
        )

        return MyViewHolder(binding)

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var items = gitRepoItemList[position]

        holder.binding.descriptionTextView.text = items.description
        holder.binding.repoNameTextView.text = items.fullName
        holder.binding.languageTitleTextView.text = items.language
        holder.binding.starCountTextView.text = items.watchersCount.toString()

        Glide.with(mCon)
            .load(items.owner?.avatarUrl)
            .apply(
                RequestOptions().placeholder(R.drawable.git_icon)
            )
            .into(holder.binding.imageIcon)

        holder.binding.llMain.setOnClickListener(View.OnClickListener {
            onClickListener.onClick(items)
        })

    }

    override fun getItemCount(): Int {
        return gitRepoItemList.size
    }


    class MyViewHolder(itemView: RowGitRepositoryListBinding) :
        RecyclerView.ViewHolder(itemView.root) {

        var binding = itemView

    }

    interface OnClickListener {
        fun onClick(items: Items)
    }
}
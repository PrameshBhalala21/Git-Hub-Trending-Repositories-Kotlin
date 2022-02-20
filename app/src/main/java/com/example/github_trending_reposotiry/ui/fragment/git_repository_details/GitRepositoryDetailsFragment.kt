package com.example.github_trending_reposotiry.ui.fragment.git_repository_details

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.github_trending_reposotiry.R
import com.example.github_trending_reposotiry.databinding.FragmentGitRepositoryDetailsBinding
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.Items
import com.example.github_trending_reposotiry.utils.DateUtils

class GitRepositoryDetailsFragment() : Fragment() {

    private lateinit var binding: FragmentGitRepositoryDetailsBinding
    private lateinit var itemsModel: Items
    private val mCon = context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_git_repository_details,
            container,
            false
        )

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments != null) {
            var argument = GitRepositoryDetailsFragmentArgs.fromBundle(arguments!!)
            setData(argument.item!!)
        }
    }

    private fun setData(items: Items) {
        itemsModel = items
        Glide.with(requireActivity())
            .load(itemsModel.owner?.avatarUrl)
            .apply(
                RequestOptions().placeholder(R.drawable.git_icon)
            ).error(R.drawable.git_icon)
            .into(binding.imageIcon)

        binding.userNameTextView.text = itemsModel.owner?.login
        binding.descriptionTextView.text = itemsModel.description
        binding.repoNameTextView.text = itemsModel.fullName
        binding.languageTitleTextView.text = itemsModel.language
        binding.starCountTextView.text = itemsModel.stargazersCount.toString()
        binding.watchCountTextView.text = itemsModel.watchersCount.toString()
        binding.frokCountTextView.text = itemsModel.forksCount.toString()
        binding.issueCountTextView.text = itemsModel.openIssuesCount.toString()
        binding.createdAtTextView.text = itemsModel.createdAt?.let { DateUtils.formatDate(it) }
        binding.updatedAtTextView.text = itemsModel.updatedAt?.let { DateUtils.formatDate(it) }
        binding.visibilityTextView.text = itemsModel.visibility
        binding.topicsTextView.text = setTopics(itemsModel.topics!!)


    }

    private fun setTopics(topicsList: ArrayList<String>): CharSequence? {
        var topics = ""

        topicsList.forEachIndexed { index, stringName ->
            topics = if (index == 0) {
                stringName
            } else {
                "$topics, $stringName"
            }
        }

        return topics

    }

}
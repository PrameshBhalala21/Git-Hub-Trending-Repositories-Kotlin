package com.example.github_trending_reposotiry.ui.fragment.git_repository_list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.github_trending_reposotiry.GitRepositoryApplication
import com.example.github_trending_reposotiry.R
import com.example.github_trending_reposotiry.databinding.FragmentGitRepositoryListBinding
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.adapter.GitRepositoryListAdapter
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.model.Items
import com.example.github_trending_reposotiry.ui.fragment.git_repository_list.viewModel.GitRepositoryListViewModel
import com.example.github_trending_reposotiry.utils.ViewModelFactory


class GitRepositoryListFragment : Fragment() {

    private lateinit var gitRepositoryListViewModel: GitRepositoryListViewModel
    private lateinit var binding: FragmentGitRepositoryListBinding
    private lateinit var adapter: GitRepositoryListAdapter
    private lateinit var navController: NavController

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
            R.layout.fragment_git_repository_list,
            container,
            false
        )


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            initialize(view)
            gitRepositoryListViewModel.gitRepositoryAPIResponse.observe(this, Observer {

                adapter.addList(it.items)
                binding.gitRepoRecyclerView.adapter = adapter
//                Toast.makeText(requireActivity(), it.items.size.toString(), Toast.LENGTH_LONG)
//                    .show()
            })

            /* gitRepositoryListViewModel.gitRepositoryDatabaseData.observe(this, Observer {
                 Toast.makeText(requireActivity(), it.size.toString(), Toast.LENGTH_LONG)
                     .show()
             })*/

            gitRepositoryListViewModel.gitLoaderResponse.observe(this, Observer {
                if (it) {
                    binding.progress.visibility = View.VISIBLE
                } else {
                    binding.progress.visibility = View.GONE

                }
            })

            gitRepositoryListViewModel.gitErrorResponse.observe(this, Observer {
                Toast.makeText(requireActivity(), it, Toast.LENGTH_LONG)
                    .show()
            })

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun initialize(view: View) {

        navController = Navigation.findNavController(view)
        binding.gitRepoRecyclerView.setHasFixedSize(true)
        binding.gitRepoRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
        val gitRepository =
            (requireActivity().application as GitRepositoryApplication).gitRepository

        gitRepositoryListViewModel = ViewModelProvider(
            this,
            ViewModelFactory(gitRepository)
        ).get(GitRepositoryListViewModel::class.java)

        adapter = GitRepositoryListAdapter(requireActivity(),
            object : GitRepositoryListAdapter.OnClickListener {
                override fun onClick(items: Items) {

                    var action =
                        GitRepositoryListFragmentDirections.actionGitRepositoryListFragmentToGitRepositoryDetailsFragment(
                            items
                        )

                    navController.navigate(action)

                    /*.... For Back Button To Close Fragment
                    val navOption =
                        NavOptions.Builder().setPopUpTo(R.id.gitRepositoryListFragment, true)
                            .build()
                    navController.navigate(
                        R.id.action_gitRepositoryListFragment_to_gitRepositoryDetailsFragment,
                        null,
                        navOption
                    )*/

                }
            })


    }

}
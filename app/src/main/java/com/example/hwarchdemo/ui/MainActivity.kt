package com.example.hwarchdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.hwarchdemo.R
import com.example.hwarchdemo.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: PostListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setAdapter()
        setClickListeners()
        subscribeToLiveData()
        viewModel.getPosts()
    }

    private fun setClickListeners() {
        binding.btnCreatePost.setOnClickListener {
            showCreatePostFragment()
        }
    }

    private fun showCreatePostFragment() {
        supportFragmentManager.beginTransaction()
            .add(
                binding.root.id, PostCreationFragment.getInstance()
            ).addToBackStack(PostCreationFragment.TAG).commit()
    }

    private fun setAdapter() {
        adapter = PostAdapter()
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

    private fun subscribeToLiveData() {
        viewModel.postsLiveData.observe(this, {
            adapter.updateList(it)
        })
    }

}
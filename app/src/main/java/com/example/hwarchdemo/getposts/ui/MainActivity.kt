package com.example.hwarchdemo.getposts.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.databinding.DataBindingUtil
import androidx.activity.viewModels
import com.example.hwarchdemo.R
import com.example.hwarchdemo.databinding.ActivityMainBinding
import com.example.hwarchdemo.createpost.ui.PostCreationFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview

@FlowPreview
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
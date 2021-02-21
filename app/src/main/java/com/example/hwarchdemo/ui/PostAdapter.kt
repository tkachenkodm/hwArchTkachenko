package com.example.hwarchdemo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hwarchdemo.R
import com.example.hwarchdemo.databinding.PostBannedBinding
import com.example.hwarchdemo.databinding.PostRegularBinding
import com.example.hwarchdemo.presentation.*

class PostAdapter() :
    ListAdapter<PostUiModel, RecyclerView.ViewHolder>(PostDiffUtils) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            POST_BANNED_ITEM_TYPE -> {
                val binding =
                    PostBannedBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PostBannedViewHolder(binding)
            }
            else -> {
                val binding =
                    PostRegularBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                PostRegularViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PostBannedViewHolder -> holder.bind(getItem(position) as PostBanned)
            is PostRegularViewHolder -> holder.bind(getItem(position) as PostRegular)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PostBanned -> POST_BANNED_ITEM_TYPE
            is PostRegular -> POST_REGULAR_ITEM_TYPE
        }
    }

    fun updateList(newList: List<PostUiModel>) {
        submitList(newList)
    }

    companion object {
        private const val POST_BANNED_ITEM_TYPE = R.layout.post_banned
        private const val POST_REGULAR_ITEM_TYPE = R.layout.post_regular
    }
}

class PostBannedViewHolder(private val binding: PostBannedBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostBanned) {
        binding.bannedPost = post
    }
}

class PostRegularViewHolder(private val binding: PostRegularBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostRegular) {
        binding.regularPost = post
    }
}

object PostDiffUtils : DiffUtil.ItemCallback<PostUiModel>() {
    override fun areItemsTheSame(oldPost: PostUiModel, newPost: PostUiModel): Boolean {
        return oldPost.id == newPost.id
    }

    override fun areContentsTheSame(oldPost: PostUiModel, newPost: PostUiModel): Boolean {
        return oldPost == newPost
    }

}
package com.example.hwarchdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hwarchdemo.databinding.PostBannedBinding
import com.example.hwarchdemo.databinding.PostRegularBinding
import com.example.hwarchdemo.presentation.*

class PostAdapter(private val posts: List<PostUiModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            is PostBannedViewHolder -> holder.bind(posts[position] as PostBanned)
            is PostRegularViewHolder -> holder.bind(posts[position] as PostRegular)
        }
    }

    override fun getItemCount(): Int = posts.size

    override fun getItemViewType(position: Int): Int {
        return when (posts[position]) {
            is PostBanned -> POST_BANNED_ITEM_TYPE
            else -> POST_REGULAR_ITEM_TYPE
        }
    }

    companion object {
        private const val POST_BANNED_ITEM_TYPE = R.layout.post_banned
        private const val POST_REGULAR_ITEM_TYPE = R.layout.post_regular
    }
}

class PostBannedViewHolder(private val binding: PostBannedBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostBanned) {
        binding.tvTitle.text = itemView.resources.getString(R.string.banned_user_title, post.userId)
    }
}

class PostRegularViewHolder(private val binding: PostRegularBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(post: PostRegular) {
        binding.tvTitle.text = itemView.resources.getString(R.string.post_title, post.title)
        binding.tvBody.text = itemView.resources.getString(R.string.post_body, post.body)

        if(post.hasWarning == UserWarning.HAS_WARNING) {
            binding.tvUserId.text = itemView.resources.getString(R.string.warned_user_title, post.userId)
            binding.root.setCardBackgroundColor(itemView.context.getColorStateList(R.color.post_warned_color))
        } else {
            binding.tvUserId.text = itemView.resources.getString(R.string.regular_user_title, post.userId)
            binding.root.setCardBackgroundColor(itemView.context.getColorStateList(R.color.white))
        }
    }
}
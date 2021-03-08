package com.example.hwarchdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hwarchdemo.R
import com.example.hwarchdemo.databinding.CreatePostFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostCreationFragment() : Fragment() {
    private val viewModel: PostListViewModel by activityViewModels()
    private lateinit var binding: CreatePostFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.create_post_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnSubmit.setOnClickListener {
            createPost()
        }
    }

    private fun createPost() {
        val title = binding.etPostTitle.text.trim().toString()
        val body = binding.etPostBody.text.trim().toString()

        viewModel.createNewPost(title, body) { postResult ->
            if (postResult) {
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(activity, getString(R.string.post_not_valid), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    companion object {
        const val TAG = "PostCreationFragment"
        fun getInstance(): PostCreationFragment {
            return PostCreationFragment()
        }
    }
}
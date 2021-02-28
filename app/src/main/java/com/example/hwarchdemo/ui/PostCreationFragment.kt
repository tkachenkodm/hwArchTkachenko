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
import com.example.hwarchdemo.domain.PostModel
import com.example.hwarchdemo.domain.UserStatus
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

@AndroidEntryPoint
class PostCreationFragment() : Fragment() {
    private val viewModel: PostListViewModel by activityViewModels()
    private lateinit var binding: CreatePostFragmentBinding
    private val disposable = CompositeDisposable()

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

        disposable.add(viewModel.createNewPost(title, body).subscribe { postResult: Boolean ->
            if (postResult) {
                parentFragmentManager.popBackStack()
            } else {
                Toast.makeText(activity, getString(R.string.post_not_valid), Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    companion object {
        const val TAG = "PostCreationFragment"
        fun getInstance(): PostCreationFragment {
            return PostCreationFragment()
        }
    }
}
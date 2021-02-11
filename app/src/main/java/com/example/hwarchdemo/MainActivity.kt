package com.example.hwarchdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hwarchdemo.databinding.ActivityMainBinding
import com.example.hwarchdemo.presentation.PostPresenter
import com.example.hwarchdemo.presentation.PostUiModel
import com.example.hwarchdemo.presentation.PostsView
import com.example.hwarchdemo.shared.PostComponent

class MainActivity : AppCompatActivity(), PostsView {
    private lateinit var binding: ActivityMainBinding
    private val presenter: PostPresenter by lazy {
        PostComponent.createPresenter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showPosts(posts: List<PostUiModel>) {
        binding.rvPosts.apply{
            adapter = PostAdapter(posts)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    override fun showError(error: String) {
        Log.e(MainActivity::class.java.name, error)
    }
}
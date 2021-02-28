package com.example.hwarchdemo.data

import android.util.Log
import com.example.hwarchdemo.domain.PostMapper
import com.example.hwarchdemo.domain.PostModel
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsRepository @Inject constructor(
    private val postListDao: PostListDao,
    private val postsService: PostsService,
    private val postMapper: PostMapper
) {

    fun getPosts(): Observable<List<PostModel>> {
        return postListDao.countPosts().flatMapObservable { count ->
            if (count == 0) {
                postsService.getPosts().flatMapObservable { posts ->
                    postListDao.storePosts(*posts.toTypedArray()).andThen(
                        postListDao.getPosts().map(postMapper::map)
                    )
                }
            } else {
                postListDao.getPosts().map(postMapper::map)
            }
        }

    }

    fun createNewPost(title: String, body: String): Completable {
        return postListDao.countPosts().flatMapCompletable { lastPostId ->
            postListDao.storePosts(
                Post(
                    userId,
                    lastPostId + 1,
                    title,
                    body
                )
            )
        }

    }

    companion object {
        private const val userId = 11
    }
}
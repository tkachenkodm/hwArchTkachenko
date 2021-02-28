package com.example.hwarchdemo.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.internal.operators.flowable.FlowableAll

@Dao
interface PostListDao {

    @Query("SELECT COUNT(*) FROM Post")
    fun countPosts(): Single<Int>

    @Query("SELECT * FROM Post ORDER BY createdAt desc")
    fun getPosts(): Observable<List<Post>>

    @Insert
    fun storePosts(vararg posts: Post): Completable

}
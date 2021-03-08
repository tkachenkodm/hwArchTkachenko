package com.example.hwarchdemo.app.di

import android.content.Context
import androidx.room.Room
import com.example.hwarchdemo.app.data.AppDatabase
import com.example.hwarchdemo.app.data.PostListDao
import com.example.hwarchdemo.getposts.data.PostsService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule() {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "local-database").build()
    }

    @Provides
    fun providePostListDao(database: AppDatabase): PostListDao {
        return database.postDao()
    }

    @Provides
    fun providePostsService(gsonConverterFactory: GsonConverterFactory): PostsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(PostsService::class.java)
    }

    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideGsonConverterFactory() : GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }
}
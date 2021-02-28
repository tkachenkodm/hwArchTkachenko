package com.example.hwarchdemo.di

import android.content.Context
import androidx.room.Room
import com.example.hwarchdemo.data.AppDatabase
import com.example.hwarchdemo.data.PostListDao
import com.example.hwarchdemo.data.PostsService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
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
    fun providePostsService(
        gsonConverterFactory: GsonConverterFactory,
        callAdapterFactory: RxJava2CallAdapterFactory
    ): PostsService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(PostsService::class.java)
    }

    @Provides
    fun provideCallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }
}
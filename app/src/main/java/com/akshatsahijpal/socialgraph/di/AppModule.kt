package com.akshatsahijpal.socialgraph.di

import android.content.Context
import com.akshatsahijpal.socialgraph.R
import com.akshatsahijpal.socialgraph.repository.auth.AuthRepoC
import com.akshatsahijpal.socialgraph.repository.main.MainBulkRepository
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppContext(
        @ApplicationContext context: Context
    ) = context

    @Singleton
    @Provides
    fun provideDispatcher() = Dispatchers.Main as CoroutineDispatcher


    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    )  = Glide.with(context).setDefaultRequestOptions(
        RequestOptions().placeholder(R.drawable.ic__cloud).error(R.drawable.ic_baseline_mood_bad_24)
            .diskCacheStrategy(
                DiskCacheStrategy.DATA
            )
    )

    @Singleton
    @Provides
    fun provideAuthRepository(@ApplicationContext context: Context) = AuthRepoC(context)


    @Singleton
    @Provides
    fun provideMasterRepository() = MainBulkRepository()
}
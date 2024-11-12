package com.mijasmarttasks.di

import com.mijasmarttasks.data.local.TaskDao
import com.mijasmarttasks.data.tasks.remote.TasksApi
import com.mijasmarttasks.data.tasks.repository.TasksRepositoryImpl
import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideTvCodeRepository(
        api: TasksApi,
        taskDao: TaskDao
    ): TasksRepository = TasksRepositoryImpl(api, taskDao)

}
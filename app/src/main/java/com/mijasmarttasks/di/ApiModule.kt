package com.mijasmarttasks.di

import com.mijasmarttasks.data.tasks.remote.TasksApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun provideTasksApi(): TasksApi = AppModule.baseRetrofit().create(TasksApi::class.java)

}
package com.mijasmarttasks.data.tasks.remote

import com.mijasmarttasks.data.tasks.model.TasksResponse
import retrofit2.http.GET

interface TasksApi {
    @GET("/")
    suspend fun getTasks(): TasksResponse
}
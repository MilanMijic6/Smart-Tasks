package com.mijasmarttasks.domain.splash.use_case

import com.google.common.truth.Truth.assertThat
import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SplashUseCasesTest {

    private lateinit var checkTasksUseCase: CheckTasksUseCase
    private lateinit var getAllTaskUseCase: GetAllTaskUseCase
    private lateinit var saveAllTaskUseCase: SaveAllTaskUseCase
    private val repository: TasksRepository = mockk()

    @Before
    fun setUp() {
        checkTasksUseCase = CheckTasksUseCase(repository)
        getAllTaskUseCase = GetAllTaskUseCase(repository)
        saveAllTaskUseCase = SaveAllTaskUseCase(repository)
    }

    @Test
    fun `CheckTasksUseCase should return true if tasks are present in the database`() = runTest {
        coEvery { repository.getTasksFromDatabase() } returns listOf(
            Task(id = "1", targetDate = "2024-11-13", dueDate = "2024-11-20", title = "Task 1", description = "Description", priority = 1)
        )

        val result = checkTasksUseCase()

        assertThat(result).isTrue()
    }

    @Test
    fun `CheckTasksUseCase should return false if no tasks are found in the database`() = runTest {
        coEvery { repository.getTasksFromDatabase() } returns emptyList()

        val result = checkTasksUseCase()

        assertThat(result).isFalse()
    }

    @Test
    fun `GetAllTaskUseCase should return tasks from the API`() = runTest {
        val tasks = listOf(
            Task(id = "1", targetDate = "2024-11-13", dueDate = "2024-11-20", title = "Task 1", description = "Description", priority = 1),
            Task(id = "2", targetDate = "2024-11-14", dueDate = "2024-11-22", title = "Task 2", description = "Another task", priority = 2)
        )
        coEvery { repository.fetchTasksFromApi() } returns tasks

        val result = getAllTaskUseCase()

        assertThat(result).isEqualTo(tasks)
    }

    @Test
    fun `SaveAllTaskUseCase should save tasks to the database`() = runTest {
        val tasks = listOf(
            Task(id = "1", targetDate = "2024-11-13", dueDate = "2024-11-20", title = "Task 1", description = "Description", priority = 1),
            Task(id = "2", targetDate = "2024-11-14", dueDate = "2024-11-22", title = "Task 2", description = "Another task", priority = 2)
        )
        coEvery { repository.saveTasks(tasks) } returns Unit

        saveAllTaskUseCase(tasks)

        coVerify { repository.saveTasks(tasks) }
    }
}

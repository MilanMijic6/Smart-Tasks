package com.mijasmarttasks.domain.taskdetais.use_case

import com.google.common.truth.Truth.assertThat
import com.mijasmarttasks.domain.task_details.repository.TaskDetailsRepository
import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.task_details.model.ItemStatus
import com.mijasmarttasks.domain.task_details.use_case.GetTaskUseCase
import com.mijasmarttasks.domain.task_details.use_case.UpdateTaskUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class TaskDetailsUseCasesTest {

    private lateinit var getTaskUseCase: GetTaskUseCase
    private lateinit var updateTaskUseCase: UpdateTaskUseCase
    private val repository: TaskDetailsRepository = mockk()

    @Before
    fun setUp() {
        getTaskUseCase = GetTaskUseCase(repository)
        updateTaskUseCase = UpdateTaskUseCase(repository)
    }

    @Test
    fun `GetTaskUseCase should return task for the given ID`() = runTest {
        val taskId = "1"
        val task = Task(
            id = taskId,
            targetDate = "2024-11-13",
            dueDate = "2024-11-20",
            title = "Task 1",
            description = "Description",
            priority = 1,
            status = ItemStatus.Unresolved
        )
        coEvery { repository.getTaskFromDatabase(taskId) } returns task

        val result = getTaskUseCase(taskId)

        assertThat(result).isEqualTo(task)
    }

    @Test
    fun `GetTaskUseCase should throw exception if task not found`() = runTest {
        val taskId = "2"
        coEvery { repository.getTaskFromDatabase(taskId) } throws NoSuchElementException("Task not found")

        try {
            getTaskUseCase(taskId)
            assert(false)
        } catch (e: NoSuchElementException) {
            assertThat(e.message).isEqualTo("Task not found")
        }
    }

    @Test
    fun `UpdateTaskUseCase should update the task in the database`() = runTest {
        val task = Task(
            id = "1",
            targetDate = "2024-11-13",
            dueDate = "2024-11-20",
            title = "Updated Task",
            description = "Updated description",
            priority = 2,
            status = ItemStatus.Resolved
        )
        coEvery { repository.updateTask(task) } returns Unit

        updateTaskUseCase(task)

        coVerify { repository.updateTask(task) }
    }
}

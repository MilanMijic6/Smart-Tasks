package com.mijasmarttasks.domain.tasks.use_case

import com.google.common.truth.Truth.assertThat
import com.mijasmarttasks.domain.tasks.model.Task
import com.mijasmarttasks.domain.tasks.repository.TasksRepository
import com.mijasmarttasks.domain.task_details.model.ItemStatus
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GetTasksForSpecificDayUseCaseTest {

    private lateinit var getTasksForSpecificDayUseCase: GetTasksForSpecificDayUseCase
    private val repository: TasksRepository = mockk()

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

    @Before
    fun setUp() {
        getTasksForSpecificDayUseCase = GetTasksForSpecificDayUseCase(repository)
    }

    @Test
    fun `should return tasks for a specific date`() = runTest {
        val targetDate: Date = dateFormat.parse("2024-11-13")!!
        val tasks = listOf(
            Task(
                id = "1",
                targetDate = "2024-11-13",
                dueDate = "2024-11-20",
                title = "Task 1",
                description = "First Task",
                priority = 1,
                status = ItemStatus.Unresolved
            ),
            Task(
                id = "2",
                targetDate = "2024-11-13",
                dueDate = "2024-11-22",
                title = "Task 2",
                description = "Second Task",
                priority = 2,
                status = ItemStatus.Resolved
            )
        )

        coEvery { repository.getTasksForSpecificDay(targetDate) } returns tasks

        val result = getTasksForSpecificDayUseCase(targetDate)

        assertThat(result).isEqualTo(tasks)
        assertThat(result[0].calculateDaysLeft()).isEqualTo(7) // Assuming today is 2024-11-13
        assertThat(result[1].calculateDaysLeft()).isEqualTo(9)
    }

    @Test
    fun `should return empty list when no tasks are found for the date`() = runTest {
        val targetDate: Date = dateFormat.parse("2024-11-13")!!
        coEvery { repository.getTasksForSpecificDay(targetDate) } returns emptyList()

        val result = getTasksForSpecificDayUseCase(targetDate)

        assertThat(result).isEmpty()
    }
}

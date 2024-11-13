package com.mijasmarttasks.domain.task_details.model

sealed class ItemStatus(
    val displayName: String
) {
    data object Unresolved : ItemStatus("Unresolved")
    data object Resolved : ItemStatus("Resolved")
    data object CantResolve : ItemStatus("CantResolve")
}
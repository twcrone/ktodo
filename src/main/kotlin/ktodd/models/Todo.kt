package ktodd.models

import kotlinx.serialization.Serializable

@Serializable
data class Todo(val id: String, val title: String)

val todoStorage = mutableListOf<Todo>()
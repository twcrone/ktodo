package ktodd.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ktodd.models.Todo
import ktodd.models.todoStorage

fun Route.todoRouting() {
    route("/todo") {
        get {
            if (todoStorage.isNotEmpty()) {
                call.respond(todoStorage)
            } else {
                call.respondText("No todos found", status = HttpStatusCode.OK)
            }
        }
        get("{id?}") {
            val id = call.parameters["id"] ?: return@get call.respondText(
                "Missing id",
                status = HttpStatusCode.BadRequest
            )
            val todo = todoStorage.find { it.id == id } ?: return@get call.respondText(
                "No todo found with id $id",
                status = HttpStatusCode.NotFound
            )
            call.respond(todo)
        }
        post {
            val todo = call.receive<Todo>()
            todoStorage.add(todo)
            call.respondText("Todo stored", status = HttpStatusCode.Created)
        }
        delete("{id?}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            if(todoStorage.removeIf { it.id == id }) {
                call.respondText("Todo removed", status = HttpStatusCode.Accepted)
            } else {
                call.respondText("Not Found", status = HttpStatusCode.NotFound)
            }

        }
    }
}
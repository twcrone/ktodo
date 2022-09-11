package ktodd

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*
import ktodd.models.Todo
import ktodd.plugins.*

class TodoRouteTest {
    @Test
    fun testPostTodo() = testApplication {
        application {
            configureRouting()
        }
        val response = client.post("/todo") {
            contentType(ContentType.Application.Json)
            setBody("""{"id": "100", "title": "Apple"}""")
        }
        assertEquals("Todo stored", response.bodyAsText())
        assertEquals(HttpStatusCode.Created, response.status)
    }
}
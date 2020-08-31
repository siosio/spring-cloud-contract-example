package siosio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@SpringBootApplication
class ServerSideApplication

fun main(args: Array<String>) {
    runApplication<ServerSideApplication>(*args)
}

@RestController
@RequestMapping("/sample")
class SampleController(
    private val sampleService: SampleService
) {

    @GetMapping("{id:\\d+}")
    fun get(@PathVariable id: Int): SampleResponse {
        return sampleService.get(id)
    }
}

@Service
class SampleService {
    fun get(id: Int): SampleResponse {
        return SampleResponse(id, "name")
    }
}

data class SampleResponse(
    val id: Int,
    val name: String,
)

@ResponseStatus(HttpStatus.NOT_FOUND)
class NotFoundException : RuntimeException()
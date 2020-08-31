package siosio

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@Configuration
class ClientServiceApplication {
    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplateBuilder().build()
    }
}

fun main(args: Array<String>) {
    runApplication<ClientServiceApplication>(*args)
}

@RestController
@RequestMapping("/sample")
class SampleController(
    private val sampleService: SampleService
) {

    @GetMapping("{id:\\d+}")
    fun get(@PathVariable id: Int): SampleApiResponse {
        return sampleService.get(id)
    }
}

@Service
class SampleService(
    private val restTemplate: RestTemplate
) {
    fun get(id: Int): SampleApiResponse {
        return restTemplate.getForEntity("http://localhost:8080/sample/{id}", SampleApiResponse::class.java, id).body!!
    }
}

data class SampleApiResponse(
    val id: Int,
    val name: String,
)

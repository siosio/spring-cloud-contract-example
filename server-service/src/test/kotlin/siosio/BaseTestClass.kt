package siosio

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.jupiter.api.BeforeEach

abstract class BaseTestClass {

    private val mockService: SampleService = mockk()

    @BeforeEach
    open fun setup() {
        val slot = slot<Int>()
        every { mockService.get(capture(slot)) } answers {
            if (slot.captured == 999) throw NotFoundException()
            SampleResponse(slot.captured, "name_${slot.captured}")
        }
        RestAssuredMockMvc.standaloneSetup(SampleController(mockService))
    }
}
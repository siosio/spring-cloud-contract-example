package siosio

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties
import org.springframework.test.context.TestConstructor
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.client.HttpClientErrorException

@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureStubRunner(ids = ["siosio:server-service:1.0.0:stubs:8080"], stubsMode = StubRunnerProperties.StubsMode.LOCAL)
internal class SampleServiceTest(
    private val sut: SampleService
) {

    @Test
    internal fun 正常系() {
        val actual = sut.get(100)
        Assertions.assertThat(actual)
            .isEqualTo(SampleApiResponse(100, "name_100"))
    }
    
    @Test
    internal fun `404系`() {
        Assertions.assertThatThrownBy { sut.get(999) }
            .isInstanceOf(HttpClientErrorException.NotFound::class.java)
    }
}
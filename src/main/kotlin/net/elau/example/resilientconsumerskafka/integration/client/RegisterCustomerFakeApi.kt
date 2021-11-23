package net.elau.example.resilientconsumerskafka.integration.client

import net.elau.example.resilientconsumerskafka.config.properties.FakeApiErrorToggleProperties
import net.elau.example.resilientconsumerskafka.exception.IntegrationClientException
import net.elau.example.resilientconsumerskafka.integration.request.CreateCustomerIntegrationRequest
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class RegisterCustomerFakeApi(
    private val fakeApiErrorToggleProperties: FakeApiErrorToggleProperties
) {
    companion object {
        private val log = LoggerFactory.getLogger(RegisterCustomerFakeApi::class.java)
        private var registerCallCount = 0
    }

    fun register(createCustomerIntegrationRequest: CreateCustomerIntegrationRequest) {
        if (fakeApiErrorToggleProperties.enabled && (++registerCallCount % fakeApiErrorToggleProperties.errorIntervalCount != 0)) {
            throw IntegrationClientException("Failed to register customer [$createCustomerIntegrationRequest]")
        }
        log.debug("c=RegisterCustomerFakeApi, m=register, msg=Customer[$createCustomerIntegrationRequest] registered on fake api")
    }
}

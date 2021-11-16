package net.elau.example.resilientconsumerskafka.integration.client

import net.elau.example.resilientconsumerskafka.config.properties.FakeApiErrorToggleProperties
import net.elau.example.resilientconsumerskafka.exception.IntegrationClientException
import net.elau.example.resilientconsumerskafka.integration.request.CreateCustomerIntegrationRequest
import org.springframework.stereotype.Component

@Component
class RegisterCustomerFakeApi(private val fakeApiErrorToggleProperties: FakeApiErrorToggleProperties) {

    fun register(createCustomerIntegrationRequest: CreateCustomerIntegrationRequest) {
        if (fakeApiErrorToggleProperties.enabled) throw IntegrationClientException("Failed to register customer [$createCustomerIntegrationRequest]")
    }
}

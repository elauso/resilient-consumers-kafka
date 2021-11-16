package net.elau.example.resilientconsumerskafka.mapper

import net.elau.example.resilientconsumerskafka.dto.CreateCustomerDto
import net.elau.example.resilientconsumerskafka.event.stream.message.CustomerCreatedEvent
import net.elau.example.resilientconsumerskafka.integration.request.CreateCustomerIntegrationRequest
import net.elau.example.resilientconsumerskafka.model.Customer
import org.mapstruct.Mapper

@Mapper
interface CustomerMapper {

    fun toDto(customerCreatedEvent: CustomerCreatedEvent): CreateCustomerDto

    fun toIntegrationRequest(createCustomerDto: CreateCustomerDto): CreateCustomerIntegrationRequest

    fun toModel(createCustomerDto: CreateCustomerDto): Customer
}

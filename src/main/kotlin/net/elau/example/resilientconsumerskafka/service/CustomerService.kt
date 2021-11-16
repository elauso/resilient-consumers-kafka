package net.elau.example.resilientconsumerskafka.service

import net.elau.example.resilientconsumerskafka.dto.CreateCustomerDto
import net.elau.example.resilientconsumerskafka.integration.client.RegisterCustomerFakeApi
import net.elau.example.resilientconsumerskafka.mapper.CustomerMapper
import net.elau.example.resilientconsumerskafka.repository.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CustomerService(
    private val customerMapper: CustomerMapper,
    private val customerRepository: CustomerRepository,
    private val registerCustomerFakeApi: RegisterCustomerFakeApi
) {
    companion object {
        private val log = LoggerFactory.getLogger(CustomerService::class.java)
    }

    @Transactional
    fun create(createCustomerDto: CreateCustomerDto) {
        log.debug("c=CustomerService,m=create,msg=Creating customer $createCustomerDto")
        customerMapper.toIntegrationRequest(createCustomerDto).let { registerCustomerFakeApi.register(it) }
        customerMapper.toModel(createCustomerDto).let { customerRepository.save(it) }
    }
}

package net.elau.example.resilientconsumerskafka.service

import net.elau.example.resilientconsumerskafka.dto.CreateCustomerDto
import net.elau.example.resilientconsumerskafka.dto.CustomerLocalRegisteredDto
import net.elau.example.resilientconsumerskafka.event.stream.producer.CustomerLocalRegisteredProducer
import net.elau.example.resilientconsumerskafka.exception.CustomerExistsException
import net.elau.example.resilientconsumerskafka.integration.client.RegisterCustomerFakeApi
import net.elau.example.resilientconsumerskafka.mapper.CustomerMapper
import net.elau.example.resilientconsumerskafka.repository.CustomerRepository
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT
import org.springframework.transaction.event.TransactionalEventListener

@Service
class CustomerService(
    private val applicationEventPublisher: ApplicationEventPublisher,
    private val customerMapper: CustomerMapper,
    private val customerRepository: CustomerRepository,
    private val registerCustomerFakeApi: RegisterCustomerFakeApi,
    private val customerLocalRegisteredProducer: CustomerLocalRegisteredProducer
) {
    companion object {
        private val log = LoggerFactory.getLogger(CustomerService::class.java)
    }

    @Transactional
    fun create(createCustomerDto: CreateCustomerDto) {
        log.debug("c=CustomerService, m=create, msg=Creating customer $createCustomerDto")
        checkIfNotExists(createCustomerDto.document)
        registerCustomerFakeApi.register(customerMapper.toIntegrationRequest(createCustomerDto))
        customerRepository.save(customerMapper.toModel(createCustomerDto))
            .also { applicationEventPublisher.publishEvent(customerMapper.toDto(it)) }
    }

    @Transactional(propagation = NOT_SUPPORTED)
    fun checkIfNotExists(document: String) {
        if (customerRepository.existsCustomerByDocument(document))
            throw CustomerExistsException("Customer with document $document exists")
    }

    @TransactionalEventListener(phase = AFTER_COMMIT)
    fun listen(customerLocalRegisteredDto: CustomerLocalRegisteredDto) {
        customerLocalRegisteredProducer.produce(customerMapper.toEvent(customerLocalRegisteredDto))
    }
}

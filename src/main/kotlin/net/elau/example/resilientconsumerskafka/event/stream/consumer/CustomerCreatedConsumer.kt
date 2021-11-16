package net.elau.example.resilientconsumerskafka.event.stream.consumer

import net.elau.example.resilientconsumerskafka.event.stream.message.CustomerCreatedEvent
import net.elau.example.resilientconsumerskafka.mapper.CustomerMapper
import net.elau.example.resilientconsumerskafka.service.CustomerService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.Message
import org.springframework.stereotype.Component
import java.util.function.Consumer

@Component
class CustomerCreatedConsumer(
    private val customerMapper: CustomerMapper,
    private val customerService: CustomerService
) : Consumer<CustomerCreatedEvent> {
    companion object {
        private val log = LoggerFactory.getLogger(CustomerCreatedConsumer::class.java)
    }

    override fun accept(customerCreatedEvent: CustomerCreatedEvent) {
        log.debug("c=CustomerCreatedConsumer, m=accept, msg=Received event $customerCreatedEvent")
        customerMapper.toDto(customerCreatedEvent).let { customerService.create(it) }
    }

    @KafkaListener(id = "customer-created-dlq", topics = ["queueing.example.customer.created.dlq"])
    fun error(message: Message<Any>) {
        log.debug("Dlq consumed: ${message.toString()}")
    }
}

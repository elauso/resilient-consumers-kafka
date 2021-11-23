package net.elau.example.resilientconsumerskafka.event.stream.producer

import net.elau.example.resilientconsumerskafka.event.stream.message.CustomerLocalRegisteredEvent
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import reactor.core.publisher.Sinks.EmitFailureHandler.FAIL_FAST
import java.util.function.Supplier

@Component
class CustomerLocalRegisteredProducer : Supplier<Flux<CustomerLocalRegisteredEvent>> {
    private val sink = Sinks.many().unicast().onBackpressureBuffer<CustomerLocalRegisteredEvent>()

    fun produce(customerLocalRegisteredEvent: CustomerLocalRegisteredEvent) {
        sink.emitNext(customerLocalRegisteredEvent, FAIL_FAST)
    }

    override fun get(): Flux<CustomerLocalRegisteredEvent> = sink.asFlux()
}

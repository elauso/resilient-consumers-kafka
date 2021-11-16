package net.elau.example.resilientconsumerskafka

import net.elau.example.resilientconsumerskafka.config.properties.FakeApiErrorToggleProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(FakeApiErrorToggleProperties::class)
class ResilientConsumersKafkaApplication

fun main(args: Array<String>) {
    runApplication<ResilientConsumersKafkaApplication>(*args)
}

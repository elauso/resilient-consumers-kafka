package net.elau.example.resilientconsumerskafka.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "toggle.fake-api-error")
data class FakeApiErrorToggleProperties(
    val enabled: Boolean,
    val errorIntervalCount: Int
)

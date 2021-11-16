package net.elau.example.resilientconsumerskafka.event.stream.message

data class CustomerCreatedEvent(

    val firstName: String,

    val lastName: String,

    val document: String,

    val email: String
)

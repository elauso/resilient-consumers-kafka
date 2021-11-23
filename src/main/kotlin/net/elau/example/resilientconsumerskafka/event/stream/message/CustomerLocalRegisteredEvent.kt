package net.elau.example.resilientconsumerskafka.event.stream.message

data class CustomerLocalRegisteredEvent(

    val id: Long,

    val firstName: String,

    val lastName: String,

    val document: String,

    val email: String
)

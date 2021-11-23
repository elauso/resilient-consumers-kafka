package net.elau.example.resilientconsumerskafka.dto

data class CustomerLocalRegisteredDto(

    val id: Long,

    val firstName: String,

    val lastName: String,

    val document: String,

    val email: String
)

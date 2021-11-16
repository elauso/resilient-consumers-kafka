package net.elau.example.resilientconsumerskafka.integration.request

data class CreateCustomerIntegrationRequest(

    val firstName: String,

    val lastName: String,

    val document: String,

    val email: String
)

package net.elau.example.resilientconsumerskafka.repository

import net.elau.example.resilientconsumerskafka.model.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long>

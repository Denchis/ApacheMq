package com.bftcom.ApacheMq.service

import com.bftcom.ApacheMq.model.Person
import com.bftcom.ApacheMq.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class PersonServiceImpl(
    private val personRepository: PersonRepository
) : PersonService {
    override fun getAll(): List<Person> = personRepository.getAll()

    override fun create(name: String,lastName: String): Int =
        personRepository.create(name, lastName)

    override fun findByNameAndLastName(name: String, lastName: String): Person? =
        personRepository.findByNameAndLastName(name,lastName)

    override fun deleteAll() {
        personRepository.deleteAll()
    }

}
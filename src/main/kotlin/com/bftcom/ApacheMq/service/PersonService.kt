package com.bftcom.ApacheMq.service

import com.bftcom.ApacheMq.model.Person

interface PersonService {
    fun getAll(): List<Person>

    fun create(name: String,lastName: String): Int

    fun findByNameAndLastName(name: String, lastName: String): Person?

    fun deleteAll()
}
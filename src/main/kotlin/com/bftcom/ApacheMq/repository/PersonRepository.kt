package com.bftcom.ApacheMq.repository

import com.bftcom.ApacheMq.model.Person

interface PersonRepository {
    fun getAll(): List<Person>

    fun findByNameAndLastName(name: String, lastName: String): Person?

    fun create(name: String, lastName: String):Int

    fun deleteAll()
}
package com.bftcom.ApacheMq

import com.bftcom.ApacheMq.controller.PersonController
import com.bftcom.ApacheMq.repository.PersonRepository
import com.bftcom.ApacheMq.repository.PersonRepositoryImpl
import com.bftcom.ApacheMq.service.PersonServiceImpl
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jms.core.JmsTemplate

@SpringBootTest
class JmsListenerTest {

    @Autowired
    lateinit var personRepository: PersonRepository

    @Autowired
    lateinit var jmsTemplate: JmsTemplate

    @Test
    fun JmsListenerTestcreateTest(){
        personRepository.deleteAll()
        val persons = """
            <persons>
               <person>
                  <name>Vasya</name>
                  <last-name>Vushii</last-name>
               </person>
            </persons>
        """.trimIndent()
        jmsTemplate.convertAndSend("person",persons)
        Thread.sleep(3000)
        val person = personRepository.findByNameAndLastName("Vasya", "Vushii")
        assertNotNull(person)
        assertEquals("Vasya",person?.name)
        assertEquals("Vushii",person?.lastName)
    }
}
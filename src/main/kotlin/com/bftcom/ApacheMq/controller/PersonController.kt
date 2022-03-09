package com.bftcom.ApacheMq.controller

import com.bftcom.ApacheMq.service.PersonService
import com.bftcom.ApacheMq.model.Person
import com.bftcom.ApacheMq.model.Persons
import org.simpleframework.xml.Serializer
import org.simpleframework.xml.core.Persister
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.jms.annotation.EnableJms
import org.springframework.jms.annotation.JmsListener
import org.springframework.jms.core.JmsTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.web.bind.annotation.*
import org.w3c.dom.Document
import org.w3c.dom.NodeList
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.xpath.XPathConstants
import javax.xml.xpath.XPathFactory

@RestController
@EnableAsync
@EnableJms
@RequestMapping("/person")
class PersonController(
    private val personService: PersonService,
) {
    @Autowired
    lateinit var jmsTemplate: JmsTemplate

    @GetMapping
    fun getAll(): List<Person> = personService.getAll()

    @Async
    @JmsListener(destination = "person")
    fun listenPersons(persons: String) {
        val serializer: Serializer = Persister()
        val personsr:Persons = serializer.read(Persons::class.java, persons)
        for (p in personsr.personList){
            if (personService.findByNameAndLastName(p.name,p.lastname) == null){
                personService.create(p.name,p.lastname)
            }else{
                println("ignoring Person{ ${p.name}, ${p.lastname} }")
            }
        }
    }

    @PostMapping(value = ["/send"], produces = [MediaType.APPLICATION_XML_VALUE])
    fun sendMessage(@RequestBody persons: String):String{
        println(persons)
        jmsTemplate.convertAndSend("person",persons)
        return "done"
    }
}

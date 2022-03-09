package com.bftcom.ApacheMq.model
import org.simpleframework.xml.ElementList

data class Persons (
    @field:ElementList(inline = true, required = false)
    var personList: ArrayList<PersonD> = arrayListOf()
){
    fun getList():ArrayList<PersonD> = personList
}
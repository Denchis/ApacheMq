package com.bftcom.ApacheMq.model


import org.simpleframework.xml.Element
import org.simpleframework.xml.Root


@Root(name = "person")
data class PersonD (
    @field:Element(name="name")
    var name: String = "",
    @field:Element(name =  "last-name")
    var lastname: String = "",
)
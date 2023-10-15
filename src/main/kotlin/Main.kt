package com.apress.prospring6.two

import org.springframework.context.ApplicationContext
import com.apress.prospring6.two.decoupled.*
import org.springframework.context.support.ClassPathXmlApplicationContext

fun main(args: Array<String>) {
   val ctx: ApplicationContext = ClassPathXmlApplicationContext("spring/app-context.xml")

    val mr = ctx.getBean("renderer", MessageRenderer::class.java)
    mr.render()
}
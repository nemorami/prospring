package com.apress.prospring6.two

import HelloWorldConfiguration
import org.springframework.context.ApplicationContext
import com.apress.prospring6.two.decoupled.*
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.support.ClassPathXmlApplicationContext

// fixme prospring-1.0-SNAPSHOT.jar에 기본 Manifest 속성이 없습니다.
fun main(args: Array<String>) {
    // Listing 2.7 page 35
   //val ctx: ApplicationContext = ClassPathXmlApplicationContext("spring/app-context.xml")
    // Listing 2-21  page 38-39
    val ctx = AnnotationConfigApplicationContext(HelloWorldConfiguration::class.java)
    val mr = ctx.getBean("renderer", MessageRenderer::class.java)
    mr.render()
}
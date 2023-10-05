package com.apress.prospring6.two

import com.apress.prospring6.two.decoupled.HelloWorldMessageProvider
import com.apress.prospring6.two.decoupled.MessageProvider
import com.apress.prospring6.two.decoupled.MessageRenderer
import com.apress.prospring6.two.decoupled.StandardOutMessageRenderer

fun main(args: Array<String>) {
// Listing 2-10
    val mr: MessageRenderer = StandardOutMessageRenderer()
    val mp: MessageProvider = HelloWorldMessageProvider()
    mr.messageProvider = mp
    mr.render()
//    if (args.size > 0) {
//        println(args[0])
//    } else {
//        println("Hello World!")
//    }



    // Try adding program arguments via Run/Debug configuration.
    // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
   // println("Program arguments: ${args.joinToString()}")
}
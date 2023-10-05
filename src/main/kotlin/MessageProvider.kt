
package com.apress.prospring6.two.decoupled

interface MessageProvider {
   val message: String?
}

interface MessageRenderer {
    fun render()
    var messageProvider: MessageProvider?
}

class HelloWorldMessageProvider : MessageProvider {
    init {
        println(" --> HelloWorldMessageProvider: constructor called")
    }

    override val message: String
        get() = "Hello World!"
}

class StandardOutMessageRenderer() : MessageRenderer {
    override fun render() {
        println(messageProvider?.message ?: throw RuntimeException("You must set the property messageProvier of class: ${StandardOutMessageRenderer::class.java.name}"))
    }

    override var messageProvider: MessageProvider? = null
        //get() = TODO("Not yet implemented")
        set(value) {
            field = value
            println(" --> StandardOutMessageRenderer: setting the provider")
        }
    init {
        println(" --> StandardOutMessageRenderer: constructor called")
    }
}

package com.apress.prospring6.two.decoupled

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

interface MessageProvider {
   val message: String?
}

interface Container {
    fun getDependency(key: String): Any?
}
interface ManagedComponent {
    fun performLookup(container: Container)
}
interface MessageRenderer : ManagedComponent {
    fun render()
    var messageProvider: MessageProvider?
}
// Listing 3-15 Configurable MessageProvider Implementation
@Component("provider") // check: default name
internal class HelloWorldMessageProvider(@Value("Configurable message") override var message: String) : MessageProvider {
    init {
        println(" --> HelloWorldMessageProvider: constructor called")
    }

//    override val message: String
//        get() = "Hello World!"
}
@Component("renderer")
class StandardOutMessageRenderer(override var messageProvider: MessageProvider? = null) : MessageRenderer {
    /* Listing 3-21 */
//    override var messageProvider: MessageProvider? = null
//        @Autowired set(value) {
//            println(" ~~ Injection dependency using setter~~")
//            field = value
//        }
    override fun render() {
        println(messageProvider?.message ?: throw RuntimeException("You must set the property messageProvider of class: ${StandardOutMessageRenderer::class.java.name}"))
    }

//    override var messageProvider: MessageProvider? = null
//        //get() = TODO("Not yet implemented")
//        set(value) {
//            field = value
//            println(" --> StandardOutMessageRenderer: setting the provider")
//        }
//
    override fun performLookup(container: Container) {
        messageProvider = container.getDependency("provider") as MessageProvider
    }

    init {
        println(" --> StandardOutMessageRenderer: constructor called")
    }
}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

@Component
class ConstructorConfusion {
    private var someValue: String

    constructor(someValue: String) {
        println("ConstructorConfusion(String) Called")
        this.someValue = someValue
    }

    @Autowired
    constructor(@Value("90") someValue: Int){
        println("ConstructorConfusion(int) called")
        this.someValue = "Number: ${someValue}" // Why "Number: " + Integer.toString(someValue)
    }

    override fun toString(): String {
        return someValue
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>){
            val ctx = AnnotationConfigApplicationContext()
            ctx.register(ConstructorConfusion::class.java)
            ctx.refresh()
            val cc = ctx.getBean(ConstructorConfusion::class.java)
            println("Does this work? $cc")
        }
    }
}
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

@Component("injectSimple")
class InjectSimpleDemo {
    @Value("john Mayer")
    private val name: String? = null

    @Value("40")
    private val age = 0

    @Value("1.92")
    private val height = 0f

    @Value("false")
    private val developer = false

    @Value("1241401112")
    private val AgeInSeconds: Long? = null
    override fun toString(): String{
        return """
            Name: $name
            Age: $age
            Age in Seconds: $AgeInSeconds
            Height: $height
            Is Developer?: $developer
            """.trimIndent()
    }

    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val ctx = AnnotationConfigApplicationContext()
            ctx.register(InjectSimpleDemo::class.java)
            ctx.refresh()
            val simple = ctx.getBean("injectSimple") as InjectSimpleDemo
            println(simple)
        }
    }

}
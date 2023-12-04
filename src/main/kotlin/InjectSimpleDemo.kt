import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

@Component("injectSimpleConfig")
internal class InjectSimpleConfig {
    val name = "John Mayer"
    val age = 40
    val height = 1.92f
    val isDeveloper = false
    val ageInSeconds = 1241401112L
}

@Component("injectSimple")
class InjectSimpleDemo {
    //@Value("john Mayer")
    @Value("#{injectSimpleConfig.name.toUpperCase()}")
    private val name: String? = null

    //@Value("40")
    @Value("#{injectSimpleConfig.age + 1}")
    private val age = 0

    //@Value("1.92")
    @Value("#{injectSimpleConfig.height}")
    private val height = 0f

    //@Value("false")
    @Value("#{injectSimpleConfig.developer}")
    private val developer = false

    //@Value("1241401112")
    @Value("#{injectSimpleConfig.ageInSeconds}")
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
            // InjectSimpleConfig를 등록하지 않으면 에러발생: Property or field 'injectSimpleConfig' cannot be found on object of type 'org.springframework.beans.factory.config.BeanExpressionContext' - maybe not public or not valid?
            ctx.register(InjectSimpleConfig::class.java,InjectSimpleDemo::class.java)
            ctx.refresh()
            val simple = ctx.getBean("injectSimple") as InjectSimpleDemo
            println(simple)
        }
    }

}
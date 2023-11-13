import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component("injectSimple")
class InjectSimpleDemo {
    @Value("john Mayer")
    private val name: String? = null

    @Value("40")
    private val age = 0

}
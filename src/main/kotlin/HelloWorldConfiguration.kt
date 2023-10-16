import com.apress.prospring6.two.decoupled.HelloWorldMessageProvider
import com.apress.prospring6.two.decoupled.MessageProvider
import com.apress.prospring6.two.decoupled.MessageRenderer
import com.apress.prospring6.two.decoupled.StandardOutMessageRenderer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class HelloWorldConfiguration {
    @Bean
    open fun provider(): MessageProvider {
        return HelloWorldMessageProvider()
    }

    @Bean // equivalent to <bean id="renderer" class=".."/>
    open fun renderer(): MessageRenderer {
        val renderer: MessageRenderer = StandardOutMessageRenderer()
        renderer.messageProvider = provider()
        return renderer
    }
}
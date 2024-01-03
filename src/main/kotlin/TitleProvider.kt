import org.springframework.beans.BeansException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

class Song(val title: String?)
class TitleProvider {
    var title: String? = "Gravity"

    companion object {
        fun instance(title: String?): TitleProvider {
            val childProvider = TitleProvider()
            if(title?.isNotBlank() ?: false)  //title != null && title.isNotBlank())
                childProvider.title = title
            return childProvider
        }
    }
}

@Configuration
open class ParentConfig {
    @Bean
    open fun parentProvider(): TitleProvider {
        return TitleProvider.instance(null)
    }

    @Bean
    open fun childProvider(): TitleProvider {
        return TitleProvider.instance("Daughters")
    }
}

@Configuration
open class ChildConfig : ApplicationContextAware {
    var applicationContext1: ApplicationContext? = null

    @Bean
    open fun childProvider() : TitleProvider {
        return TitleProvider.instance("No Sunch Thing")
    }

    @Bean
    open fun song1(@Value("#{parentProvider.title}") title: String?): Song {
        return Song(title)
    }

    @Bean
    open fun song2(@Value("#{childConfig.applicationContext1.parent.getBean(\"childProvider\").title}")
    title: String?) : Song {
        return Song(title)
    }

    @Bean
    open fun song3(@Value("#{childProvider.title}") title: String?) : Song {
        return Song(title)
    }

    @Throws(BeansException::class)
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext1 = applicationContext
    }
}
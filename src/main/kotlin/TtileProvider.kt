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
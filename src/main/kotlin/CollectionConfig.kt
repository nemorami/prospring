import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
internal open class CollectionConfig {

    @Bean
    open fun list() : List<Song> {
        return listOf(
            Song("Not the end"),
            Song("Rise up")
        )
    }

    @Bean
    open fun set() : Set<Song> {
        return setOf(
            Song("Ordinary Day"),
            Song("Birds Fly")
        )
    }
    @Bean
    open fun map() : Map<String, Song> {
        return mapOf(
            "John Mayer" to Song("Gravity"),
            "Ben Barnes" to Song("11:11")
        )
    }

    @Bean
    open fun props() : Properties {
        val props = Properties()
        props["said.she"] = "Never Mine"
        props["said.he"] = "Cold and jaded"
        return props
    }

    @Bean
    open fun song1() : Song {
        return Song("Here's to hoping")
    }

    @Bean
    open fun song2() : Song {
        return Song("Wishing the best for you")
    }
}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component
import java.util.*
import java.util.function.Consumer
//import jakarta.annotation. CHECK how to include jakarta?

object CollectionInjectionDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = AnnotationConfigApplicationContext()
        ctx.register(CollectionConfig::class.java, CollectingBean::class.java)
        ctx.refresh()
        val collectingBean = ctx.getBean(CollectingBean::class.java)
        collectingBean.printCollections()
    }
}

@Component
internal class CollectingBean {
    /*
    @Autowired
    @Qualifier("list")
    */
    @Resource(name="list")
    var songListResource: List<Song>? = null

    @Autowired
    var songList:List<Song>? = null

    @Autowired
    var songSet: Set<Song>? = null

    @Autowired
    @Qualifier("set")
    var songSetResource: Set<Song>? = null

    @Autowired
    var songMap: Map<String, Song>? = null

    @Autowired
    @Qualifier("map")
    var songMapResource: Map<String, Song>? = null

    @Autowired
    var props: Properties? = null
    fun printCollections() {
        println("-- list injected using @Autowired --")
        songList!!.forEach(Consumer<Song> { s: Song -> println(s.title)})
        println("""-- list injected using @Resouurce / @Autowired @Qualifier("list") /
            |@Inject @named("list") --
        """.trimMargin())
        songListResource!!.forEach{println(it.title)}

        println("-- set injected using @Autowired -- --")
        songSet!!.forEach {println(it.title)}

        println("""-- set injected using @Resource @Autowired @Qualifier("Set") """)
        songSetResource!!.forEach{println(it.title)}

        println("-- map injected using @Autowired --")
        songMap!!.forEach {println("${it.key}: ${it.value.title}")}
        println("""-- map injected using @Resorce""")
        songMapResource!!.forEach {println("${it.key}: ${it.value.title}")}
        println("-- props injected with @Autowired --")
        props!!.forEach {print("${it.key}: ${it.value}")  }

    }
    


}
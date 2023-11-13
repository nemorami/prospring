import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component

@Component
internal class Inspiration(@Value("For all my running, I can understand") lyric: String) {
    var  lyric = "I can keep the door cracked open, to let light through"

    init {
        this.lyric = lyric
    }
}
@Component("singer")
internal class Singer {
    @Autowired
    private val inspirationBean: Inspiration? = null

    fun sing() {
        println("... ${inspirationBean!!.lyric}")
    }
}
object SingerFieldInjectionDemo {

    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = AnnotationConfigApplicationContext()
        ctx.register(Singer::class.java, Inspiration::class.java)
        ctx.refresh()
        val singerBean = ctx.getBean(Singer::class.java)
        singerBean.sing()
    }
}
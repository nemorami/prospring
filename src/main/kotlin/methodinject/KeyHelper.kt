package methodinject

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Lookup
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch


object MethodInjectionDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val ctx = AnnotationConfigApplicationContext(LookupConfig::class.java)
        val abstractLockOpener = ctx.getBean("abstractLockOpener", LockOpener::class.java)
        val standardLockOpener = ctx.getBean("standardLockOpener", LockOpener::class.java)
        displayInfo("abstractLockOpener", abstractLockOpener)
        displayInfo("standardLockOpener", standardLockOpener)
    }

    fun displayInfo(beanName: String, lockOpener: LockOpener) {
        val keyHelperOne = lockOpener.createKeyOpener()
        val keyHelperTwo = lockOpener.createKeyOpener()

        println("[$beanName]: keyHelper Instances the Same? ${(keyHelperOne === keyHelperTwo)}")
        val stopWatch = StopWatch()
        stopWatch.start("lookupDemo")
        for(x in 0..100000 -1) {
            val keyHelper = lockOpener.createKeyOpener()
            keyHelper!!.open()
        }
        stopWatch.stop()
        println("100000 gets took ${stopWatch.totalTimeMillis} ms")
    }
}
@Component("keyHelper")
@Scope("prototype")
class KeyHelper {
    fun open() {

    }
}

// Listing 3-31 Singleton bean Interface Type

interface LockOpener {
    fun createKeyOpener(): KeyHelper?
    fun openLock()
}

// Listing 3-32

@Component("standardLockOpener")
internal class StandardLockOpener : LockOpener {
    var keyOpener: KeyHelper? = null
    override fun createKeyOpener(): KeyHelper? {
        return keyOpener
    }

    override fun openLock() {
        keyOpener!!.open()
    }

    @Autowired
    @Qualifier("keyHelper")
    fun setKeyHelper(keyHelper: KeyHelper?) {
        keyOpener = keyHelper
    }
}

// Listing 3-33

@Component("abstractLockOpener")
internal abstract class AbstractLockOpener : LockOpener {
    @Lookup("keyHelper")
    abstract override fun createKeyOpener(): KeyHelper?

    override fun openLock() {
        createKeyOpener()!!.open()
    }
}

//Listing 3-34

@Configuration
@ComponentScan
internal open class LookupConfig
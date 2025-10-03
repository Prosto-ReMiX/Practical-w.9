import kotlin.concurrent.thread

fun main() {
    val threads = Array(5) { i ->
        thread { sayHello("Thread $i") }
    }
    threads.forEach { it.start() }
}


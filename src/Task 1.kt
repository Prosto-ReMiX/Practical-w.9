import kotlin.concurrent.thread

fun main() {
    val thread = thread {
        sayMeaw()
    }
    thread.start()
}


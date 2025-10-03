import kotlin.concurrent.thread

fun main() {
    val firstThread = thread {
        println("Первый поток запущен")

        val secondThread = thread {
            try {
                while (true) {
                    println("  Второй поток работает...")
                    Thread.sleep(500)
                }
            } catch (e: InterruptedException) {
                println("  Второй поток найден и убит")
            }
        }

        secondThread.start()

        try {
            while (true) {
                println("Первый поток работает...")
                Thread.sleep(500)
            }
        } catch (e: InterruptedException) {
            println("Первый поток найден, ищю второй поток...")
            secondThread.interrupt()
        }

        println("Первый поток убит")
    }

    firstThread.start()

    Thread.sleep(2000)

    println(">>> Убиваем первый поток <<<")
    firstThread.interrupt()
}

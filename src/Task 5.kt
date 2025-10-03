import kotlin.concurrent.thread

fun main() {
    val thread = thread {
        var counter = 0
        try {
            do {
                println("Working... $counter")
                counter++
                Thread.sleep(500)
            }
            while (counter < 10)
        }
        catch (e: InterruptedException) {
            println("${e.message}")
            println("Поток прибили во сне, сволочи!")
        }
    }
    val threadKiller = thread {
        Thread.sleep(2500)
        println("Щас убью!")
        thread.interrupt()
    }

    thread.start()
    threadKiller.start()
}
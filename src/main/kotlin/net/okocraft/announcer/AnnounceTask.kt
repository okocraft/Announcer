package net.okocraft.announcer

import java.util.LinkedList
import java.util.logging.Logger

class AnnounceTask(private val messages: LinkedList<String>, private val logger: Logger): Runnable {
    override fun run() {
        val message = messages.pop()

        logger.info(message)

        messages.addLast(message)
    }
}
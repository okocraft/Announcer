package net.okocraft.announcer

import org.bukkit.Bukkit

class AnnounceTask(private val config: Config): Runnable {
    override fun run() {
        val messages = config.messages
        val message = messages.pop()

        Bukkit.getOnlinePlayers().forEach { it.sendMessage(config.prefix + message) }

        messages.addLast(message)
    }
}
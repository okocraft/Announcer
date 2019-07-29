package net.okocraft.announcer

import org.bukkit.Bukkit

class AnnounceTask(private val config: Config): Runnable {
    override fun run() {
        val prefix = config.prefix
        val messages = config.messages
        val message = messages.pop()

        Bukkit.getOnlinePlayers().forEach { player ->
            message.split("\n").forEach { m -> player.sendMessage(prefix + m) }
        }

        messages.addLast(message)
    }
}
package net.okocraft.announcer

import org.bukkit.Bukkit

class AnnounceTask(private val config: Config): Runnable {
    override fun run() {
        val messages = config.messages
        val pending = messages.pop()

        val messageList = pending.split("\n")

        Bukkit.getOnlinePlayers().forEach { player ->
            messageList.forEach { message -> player.sendMessage(config.prefix + message) }
        }

        messages.addLast(pending)
    }
}
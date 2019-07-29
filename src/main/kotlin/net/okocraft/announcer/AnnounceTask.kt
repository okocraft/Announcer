package net.okocraft.announcer

import java.util.LinkedList

import org.bukkit.Bukkit

class AnnounceTask(private val messages: LinkedList<String>): Runnable {
    override fun run() {
        val message = messages.pop()

        Bukkit.getOnlinePlayers().forEach { it.sendMessage(message) }

        messages.addLast(message)
    }
}
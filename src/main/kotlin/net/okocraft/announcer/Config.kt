package net.okocraft.announcer

import org.bukkit.ChatColor
import java.util.LinkedList
import java.util.Optional

class Config(private val plugin: Announcer) {
    var messages: LinkedList<String>
        private set
    var period: Long
        private set
    var prefix: String
        private set

    init {
        val config = plugin.config

        messages = LinkedList(config.getStringList("messages").map { translateColor(it) })
        period = config.getLong("general.period") * 20

        prefix = translateColor(
            Optional.ofNullable(config.getString("general.prefix")).orElse("")
        )
    }

    fun reload() {
        plugin.reloadConfig()

        val config = plugin.config

        messages = LinkedList(config.getStringList("messages"))
        period   = config.getLong("general.period")
        prefix   = Optional.ofNullable(config.getString("general.prefix")).orElse("")
    }

    private fun translateColor(message: String) = ChatColor.translateAlternateColorCodes('&', message)
}
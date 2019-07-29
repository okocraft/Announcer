package net.okocraft.announcer

import java.util.LinkedList

class Config(private val plugin: Announcer) {
    var messages: LinkedList<String> private set
    var period: Long private set

    init {
        val config = plugin.config

        messages = LinkedList(config.getStringList("messages"))
        period   = config.getLong("general.period")
    }

    fun reload() {
        plugin.reloadConfig()

        val config = plugin.config

        messages = LinkedList(config.getStringList("messages"))
        period   = config.getLong("general.period")
    }
}
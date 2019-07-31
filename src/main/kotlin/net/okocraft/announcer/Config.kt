/*
 * This is a part of Announcer.
 * Copyright (C) 2019 AKANE AKAGI (akaregi)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package net.okocraft.announcer

import java.util.LinkedList
import kotlin.properties.Delegates

import org.bukkit.ChatColor

/**
 * @author AKANE AKAGI (akaregi)
 */
class Config(private val plugin: Announcer) {
    lateinit var messages: LinkedList<String>
        private set
    var period: Long by Delegates.notNull()
        private set
    lateinit var prefix: String
        private set

    init {
        reload()
    }

    fun reload() {
        plugin.reloadConfig()

        val config = plugin.config

        messages = LinkedList(config.getStringList("messages").map { translateColor(it) })
        period = config.getLong("general.period") * 20
        prefix = translateColor(config.getString("general.prefix") ?: "")
    }

    private fun translateColor(message: String) = ChatColor.translateAlternateColorCodes('&', message)
}
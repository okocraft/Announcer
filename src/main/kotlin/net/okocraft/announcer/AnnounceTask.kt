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

import org.bukkit.Bukkit

/**
 * @author AKANE AKAGI (akaregi)
 */
class AnnounceTask(private val config: Config): Runnable {
    override fun run() {
        val prefix = config.prefix
        val messages = config.messages
        val message = messages.pop().also { messages.addLast(it) }

        Bukkit.getOnlinePlayers().forEach { player ->
            message.split("\n").forEach { m -> player.sendMessage(prefix + m) }
        }
    }
}
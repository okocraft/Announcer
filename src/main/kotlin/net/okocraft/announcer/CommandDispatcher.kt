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

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

/**
 * @author AKANE AKAGI (akaregi)
 */
class CommandDispatcher(private val plugin: Announcer) : CommandExecutor, TabCompleter {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val config = plugin.configuration

        if (args.first() == "reload") {
            plugin.scheduler.cancelTasks(plugin)

            config.reload()
            plugin.runAnnounceTask()
        }

        if (args.first() == "list") {
            val prefix = config.prefix

            sender.sendMessage("")

            config.messages.forEachIndexed  { index, message ->
                sender.sendMessage("${ChatColor.GRAY}${index + 1}:")
                message.split("\n").forEach { sender.sendMessage(prefix + it) }

                sender.sendMessage("")
            }
        }

        return true
    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String, args: Array<out String>): MutableList<String> {
        if (command.name != "announcer") {
            return mutableListOf()
        }

        if (args.size != 1) {
            return mutableListOf()
        }

        if (args.isEmpty()) {
            return mutableListOf("list", "reload")
        }

        if (args.size == 1) {
            if ("list".startsWith(args[0])) {
                return mutableListOf("list")
            }

            if ("reload".startsWith(args[0])) {
                return mutableListOf("reload")
            }
        }

        return mutableListOf()
    }
}
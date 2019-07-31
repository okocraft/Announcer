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

package net.okocraft.announcer.command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

/**
 * @author AKANE AKAGI (akaregi)
 */
class TabCompleterImpl: TabCompleter {
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
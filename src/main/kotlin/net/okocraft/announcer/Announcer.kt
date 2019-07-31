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

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitScheduler

import net.okocraft.announcer.command.CommandDispatcher
import net.okocraft.announcer.command.TabCompleterImpl

/**
 * @author AKANE AKAGI (akaregi)
 */
class Announcer: JavaPlugin() {
    val configuration: Config
    val scheduler: BukkitScheduler

    init {
        saveDefaultConfig()

        configuration = Config(this)
        scheduler = server.scheduler
    }

    override fun onEnable() {
        runAnnounceTask()

        getCommand("announcer")?.let {
            it.setExecutor(CommandDispatcher(this))
            it.tabCompleter = TabCompleterImpl()
        }
    }

    fun runAnnounceTask() =
        scheduler.runTaskTimerAsynchronously(this, AnnounceTask(configuration), 0L, configuration.period)
}
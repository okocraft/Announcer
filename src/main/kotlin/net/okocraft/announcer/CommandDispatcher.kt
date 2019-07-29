package net.okocraft.announcer

import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CommandDispatcher(private val plugin: Announcer) : CommandExecutor {
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
                sender.sendMessage("${ChatColor.GRAY}$index:")
                message.split("\n").forEach { sender.sendMessage(prefix + it) }

                sender.sendMessage("")
            }
        }

        return true
    }
}
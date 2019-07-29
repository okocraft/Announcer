package net.okocraft.announcer

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class Announcer: JavaPlugin() {
    private val config: Config = Config(this)

    override fun onEnable() {
        saveDefaultConfig()

        runAnnounceTask()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.first() == "reload") {
            server.scheduler.cancelTasks(this)

            config.reload()
            runAnnounceTask()
        }

        return true
    }

    private fun runAnnounceTask() {
        server.scheduler.runTaskTimerAsynchronously(
            this, AnnounceTask(config.messages, logger), 0L, config.period
        )
    }
}
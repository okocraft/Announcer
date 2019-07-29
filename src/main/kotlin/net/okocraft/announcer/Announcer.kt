package net.okocraft.announcer

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitScheduler

class Announcer: JavaPlugin() {
    private val config: Config
    private val scheduler: BukkitScheduler

    init {
        saveDefaultConfig()

        config = Config(this)
        scheduler = server.scheduler
    }

    override fun onEnable() {
        runAnnounceTask()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.first() == "reload") {
            scheduler.cancelTasks(this)

            config.reload()
            runAnnounceTask()
        }

        return true
    }

    private fun runAnnounceTask() =
        scheduler.runTaskTimerAsynchronously(this, AnnounceTask(config), 0L, config.period)
}
package net.okocraft.announcer

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitScheduler
import java.util.*

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

        Optional.ofNullable(getCommand("announcer")).ifPresent {
            it.setExecutor(CommandDispatcher(this))
        }
    }

    fun runAnnounceTask() =
        scheduler.runTaskTimerAsynchronously(this, AnnounceTask(configuration), 0L, configuration.period)
}
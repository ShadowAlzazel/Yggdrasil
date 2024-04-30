package me.shadowalzazel.yggdrasil.commands.admin

import me.shadowalzazel.yggdrasil.Yggdrasil
import me.shadowalzazel.yggdrasil.constants.CookieKeys
import me.shadowalzazel.yggdrasil.futures.CookieAwait
import me.shadowalzazel.yggdrasil.futures.CookieAwaitTask
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("UnstableApiUsage")
object GetCookieString : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (!sender.isOp) return false
        if (args == null) return false
        if (args.size != 1) return false
        // Get Args
        val name = args[0]
        if (name != sender.name) return false
        // Key
        val key = CookieKeys.COMMAND_STRING_TEST.key
        println("Starting fetch of cookie [$key]")
        val cookie = CookieAwait(key, sender)
        val outcome = cookie.getFutureValue()
        // If took to long
        if (cookie.outcome.isEmpty()) {
            println("Trying Asynchronously...")
            val awaitTask = CookieAwaitTask(cookie, 3)
            awaitTask.runTaskTimerAsynchronously(Yggdrasil.instance, 0, 5)
        }
        else {
            println("Outcome List: ${outcome.toList()}")
            println("Outcome UTF-8: ${outcome.toString(Charsets.UTF_8)}")
        }

        return true
    }

}
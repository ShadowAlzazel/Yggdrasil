package me.shadowalzazel.yggdrasil.commands.admin

import me.shadowalzazel.yggdrasil.Yggdrasil
import me.shadowalzazel.yggdrasil.cookies.CookieKeys
import me.shadowalzazel.yggdrasil.futures.AwaitFuture
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

        val key = CookieKeys.COMMAND_STRING_TEST.key
        println("Starting fetch of cookie $key")
        AwaitFuture(key, sender, 3).runTaskLater(Yggdrasil.instance, 20 * 1)

        return true
    }

}
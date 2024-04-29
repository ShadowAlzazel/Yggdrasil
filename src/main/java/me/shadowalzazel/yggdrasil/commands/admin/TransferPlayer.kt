package me.shadowalzazel.yggdrasil.commands.admin

import me.shadowalzazel.yggdrasil.cookies.CookieKeys
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("UnstableApiUsage")
object TransferPlayer : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (!sender.isOp) return false
        if (args == null) return false
        if (args.size != 3) return false
        // Get Args
        val name = args[0]
        if (name != sender.name) return false
        val host = args[1]
        val port = args[2].toInt()
        if (port > 25566) return false
        println("Starting Transfer to server [$host:$port] for $name")
        try {
            sender.transfer(host, port)
            return true
        }
        catch (except: IllegalStateException) {
            println("Failed to Transfer")
        }
        return false
    }

}
package me.shadowalzazel.yggdrasil.commands.admin

import me.shadowalzazel.yggdrasil.player_data.CookieInventoryManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("UnstableApiUsage")
object SaveInventoryCookies : CommandExecutor, CookieInventoryManager {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (!sender.isOp) return false
        if (args == null) return false
        if (args.size != 1) return false
        // Get Args
        val name = args[0]
        if (name != sender.name) return false
        saveInventoryToCookies(sender)
        return true
    }

}
package me.shadowalzazel.yggdrasil.commands.admin

import me.shadowalzazel.yggdrasil.constants.CookieKeys
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

@Suppress("UnstableApiUsage")
object SetCookieString : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        if (sender !is Player) return false
        if (!sender.isOp) return false
        if (args == null) return false
        if (args.isEmpty()) return false
        // Get Args
        val argsBytes = args.toList().toString().toByteArray(Charsets.UTF_8)
        val key = CookieKeys.COMMAND_STRING_TEST.key
        println("Set Cookie [$key] to Bytes: $argsBytes")
        println("Set Cookie [$key] to UTF-8: ${argsBytes.toString(Charsets.UTF_8)}")
        sender.storeCookie(CookieKeys.COMMAND_STRING_TEST.key, argsBytes)
        return true
    }

}
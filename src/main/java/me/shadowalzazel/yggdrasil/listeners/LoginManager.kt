package me.shadowalzazel.yggdrasil.listeners

import me.shadowalzazel.yggdrasil.cookies.CookieKeys
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent

@Suppress("UnstableApiUsage")
object LoginManager : Listener {

    @EventHandler
    fun playerLoginHandler(event: PlayerLoginEvent) {

    }

    @EventHandler
    fun playerJoinHandler(event: PlayerJoinEvent) {
        val player = event.player
        val helloWorld = "Hello World!".toByteArray()
        player.storeCookie(CookieKeys.TEST.key, helloWorld)
    }



}
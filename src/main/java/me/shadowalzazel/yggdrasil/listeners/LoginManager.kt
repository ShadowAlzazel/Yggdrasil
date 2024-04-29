package me.shadowalzazel.yggdrasil.listeners

import me.shadowalzazel.yggdrasil.cookies.CookieKeys
import me.shadowalzazel.yggdrasil.player_data.InventoryManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

@Suppress("UnstableApiUsage")
object LoginManager : Listener, InventoryManager {

    @EventHandler
    fun playerLoginHandler(event: PlayerLoginEvent) {

    }

    @EventHandler
    fun playerJoinHandler(event: PlayerJoinEvent) {
        // Test
        val player = event.player
        val helloWorld = "Hello World!".toByteArray()
        player.storeCookie(CookieKeys.TEST.key, helloWorld)
        // Get Inventory from cookies
        val inventory = getInventoryFromCookies(player)
    }

    @EventHandler
    fun playerQuitHandler(event: PlayerQuitEvent) {
        // Save Inventory to cookies

    }

}
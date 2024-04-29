package me.shadowalzazel.yggdrasil.listeners

import me.shadowalzazel.yggdrasil.player_data.CookieInventoryManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

@Suppress("UnstableApiUsage")
object LoginManager : Listener, CookieInventoryManager {

    @EventHandler
    fun playerLoginHandler(event: PlayerLoginEvent) {

    }

    @EventHandler
    fun playerJoinHandler(event: PlayerJoinEvent) {
        val player = event.player
        // Test
        //val helloWorld = "Hello World!".toByteArray()
        //player.storeCookie(CookieKeys.TEST.key, helloWorld)
        // Get Inventory from cookies

        //val cookieInventory = getInventoryFromCookies(player)
    }

    @EventHandler
    fun playerQuitHandler(event: PlayerQuitEvent) {
        // Save Inventory to cookies
        //val player = event.player
        //println("SAVING COOKIES FOR [${player.name}]")
        //saveInventoryToCookies(player)
    }

}
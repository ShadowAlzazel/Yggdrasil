package me.shadowalzazel.yggdrasil.listeners

import kotlinx.coroutines.DelicateCoroutinesApi
import me.shadowalzazel.yggdrasil.player_data.CookiePlayerManager
import me.shadowalzazel.yggdrasil.synchronizers.PlayerSynchronizer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

@DelicateCoroutinesApi
@Suppress("UnstableApiUsage")
object LoginManager : Listener, CookiePlayerManager {

    @EventHandler
    fun playerLoginHandler(event: PlayerLoginEvent) {

    }

    // Get Inventory from cookies on join for SHARED servers
    @EventHandler
    fun playerJoinHandler(event: PlayerJoinEvent) {
        val player = event.player
        val synchro = PlayerSynchronizer(player)
        synchro.setPlayerInventory()
    }

    // Save Inventory to cookie
    @EventHandler
    fun playerQuitHandler(event: PlayerQuitEvent) {
        val player = event.player
        player.saveInventoryToCookies()
    }

}
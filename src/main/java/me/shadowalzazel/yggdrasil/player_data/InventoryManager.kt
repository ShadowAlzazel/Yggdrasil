package me.shadowalzazel.yggdrasil.player_data

import org.bukkit.entity.Player

interface InventoryManager {

    // TO DO: HANDLE MULTIPLE COOKIES/ CROSSES

    fun saveInventoryToCookies(player: Player) {
        // Create new snapshot of inventory
        val cookieInventory = CookieInventory(player)
        cookieInventory.setFromPlayerInventory()

    }

    fun getInventoryFromCookies(player: Player): CookieInventory {
        // Create new snapshot of inventory
        val cookieInventory = CookieInventory(player)
        cookieInventory.setFromPlayerCookies()

        return cookieInventory
    }
    // BY DEFAULT, previous item are deleted!

}
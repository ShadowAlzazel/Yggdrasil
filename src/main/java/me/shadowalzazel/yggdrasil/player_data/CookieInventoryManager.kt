package me.shadowalzazel.yggdrasil.player_data

import me.shadowalzazel.yggdrasil.Yggdrasil
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

@Suppress("UnstableApiUsage")
interface CookieInventoryManager {

    // TO DO: HANDLE MULTIPLE COOKIES/ CROSSES

    // BY DEFAULT, previous item are deleted!

    fun Player.setInventoryFromCookies(inventory: CookieInventory) {
        if (inventory.player != this) return


    }

    // Create new snapshot of inventory
    fun saveInventoryToCookies(player: Player) {
        val cookieInventory = CookieInventory(player)
        cookieInventory.createFromPlayerInventory()
        for (slot in cookieInventory.SLOT_MAP_INDEX) {
            val key = NamespacedKey(Yggdrasil.instance, slot.key)
            val item = player.inventory.getItem(slot.value) ?: continue
            val nameAsBytes = item.type.name.toByteArray(Charsets.UTF_8)
            player.storeCookie(key, nameAsBytes)
            println("Stored Cookie: $key with $nameAsBytes")
        }

    }

    // Create new snapshot of inventory
    fun getInventoryFromCookies(player: Player): CookieInventory {
        val cookieInventory = CookieInventory(player)
        cookieInventory.createFromCookies()
        return cookieInventory
    }

}
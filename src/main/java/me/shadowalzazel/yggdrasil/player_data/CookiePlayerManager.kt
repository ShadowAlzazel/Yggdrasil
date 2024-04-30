package me.shadowalzazel.yggdrasil.player_data

import me.shadowalzazel.yggdrasil.Yggdrasil
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player

@Suppress("UnstableApiUsage")
interface CookiePlayerManager {

    // TO DO: HANDLE MULTIPLE COOKIES/ CROSSES
    // BY DEFAULT, previous item are replaced!

    fun Player.setInventoryFromCookies(inventory: CookieInventory) {
        if (inventory.player != this) return
        //println("Setting inventory from cookies for [${this.name}]")
    }

    // Create new snapshot of inventory
    fun Player.saveInventoryToCookies() {
        val cookieInventory = CookieInventory(this)
        for (slot in cookieInventory.INVENTORY_SLOTS_INDEX) {
            val key = NamespacedKey(Yggdrasil.instance, slot.key)
            val item = this.inventory.getItem(slot.value) ?: continue
            val nameAsBytes = item.type.name.toByteArray(Charsets.UTF_8)
            this.storeCookie(key, nameAsBytes)
            //println("Stored Cookie: [$key] as byte: $nameAsBytes")
            //println("Stored Cookie: [$key] as name: ${nameAsBytes.toString(Charsets.UTF_8)}")
        }

    }

    // Create new snapshot of inventory
    fun getInventoryFromCookies(player: Player): CookieInventory {
        val cookieInventory = CookieInventory(player)
        cookieInventory.createFromCookies()
        return cookieInventory
    }

}
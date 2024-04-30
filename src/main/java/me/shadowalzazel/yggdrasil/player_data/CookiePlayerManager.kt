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

    // TODO: Create a tag that disables saving

    // Create new snapshot of inventory
    fun Player.saveInventoryToCookies() {
        val cookieInventory = CookieInventory(this)
        for (slot in cookieInventory.INVENTORY_SLOTS_INDEX) {
            val nameKey = NamespacedKey(Yggdrasil.instance, slot.key)
            val byteKey = NamespacedKey(Yggdrasil.instance, "${slot.key}_bytes")
            val item = this.inventory.getItem(slot.value) ?: continue
            val nameAsBytes = item.type.name.toByteArray(Charsets.UTF_8)
            val itemAsBytes = item.serializeAsBytes()
            /*
            val metaString = item.itemMeta.asString
            println("Item: ${item.type.name}")
            println("B Meta: $metaString")
            val deserialized = ItemStack.deserializeBytes(itemAsBytes)
            println("Deserialized: $deserialized")
            println("D MEta: ${deserialized.itemMeta.asString}")
            println("Size: ${itemAsBytes.size}")
             */
            this.storeCookie(nameKey, nameAsBytes)
            this.storeCookie(byteKey, itemAsBytes)
            println("Stored Cookie [${slot.key}] with item [${item.type.name}")
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
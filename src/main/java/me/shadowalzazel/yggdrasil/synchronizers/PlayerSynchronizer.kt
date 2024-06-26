package me.shadowalzazel.yggdrasil.synchronizers

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import me.shadowalzazel.yggdrasil.Yggdrasil
import me.shadowalzazel.yggdrasil.futures.CookieAwait
import me.shadowalzazel.yggdrasil.player_data.CookieInventory
import me.shadowalzazel.yggdrasil.player_data.CookiePlayerManager
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

@DelicateCoroutinesApi
class PlayerSynchronizer(val player: Player): CookiePlayerManager {

    private val cookieInventory = CookieInventory(player)

    fun setPlayerInventory() {
        GlobalScope.launch {
            getPlayerCookieInventory()
            // Run set inventory
            if (cookieInventory.SLOT_ITEMS.isNotEmpty()) {
                for (itemSlot in cookieInventory.SLOT_ITEMS) {
                    val slotIndex = cookieInventory.INVENTORY_SLOTS_INDEX[itemSlot.key] ?: continue
                    player.inventory.setItem(slotIndex, itemSlot.value)
                }
            }
        }
    }

    // Called when needing to get inventory from cookies
    private suspend fun getPlayerCookieInventory() {
        val cookieMap = cookieInventory.createCookieMap()
        delay(10L)
        for (slot in cookieMap) {
            // Get material if not continue
            val outcome = slot.value.getFutureValue()
            val itemName = outcome.toString(Charsets.UTF_8)
            val material = Material.getMaterial(itemName) ?: continue
            // Get Material
            val byteKey = NamespacedKey(Yggdrasil.instance, "${slot.key}_bytes")
            val itemCookie = CookieAwait(byteKey, player)
            itemCookie.getFutureValue()
            val itemOutcome = itemCookie.outcome
            if (itemOutcome.isEmpty()) continue
            val deserializedItem = ItemStack.deserializeBytes(itemOutcome)
            if (deserializedItem.type != material) continue
            println("[${slot.key}]: $itemName")
            cookieInventory.SLOT_ITEMS[slot.key] = deserializedItem
        }
    }

}
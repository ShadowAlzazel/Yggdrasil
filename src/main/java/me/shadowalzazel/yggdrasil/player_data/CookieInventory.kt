package me.shadowalzazel.yggdrasil.player_data

import me.shadowalzazel.yggdrasil.Yggdrasil
import me.shadowalzazel.yggdrasil.futures.AwaitFuture
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CookieInventory(private val player: Player) {

    val SLOT_KEYS: List<String> = listOf(
        "mainhand", "offhand", "helmet", "chestplate", "leggings", "boots"
    )

    var mainhand: ItemStack = ItemStack(Material.AIR) // Temp format
    var offhand: ItemStack = ItemStack(Material.AIR)
    var helmet: ItemStack = ItemStack(Material.AIR)
    var chestplate: ItemStack = ItemStack(Material.AIR)
    var leggings: ItemStack = ItemStack(Material.AIR)
    var boots: ItemStack = ItemStack(Material.AIR)

    fun setFromPlayerInventory() {
        player.equipment.also { // TEMP
            this.mainhand = it.itemInMainHand
            this.offhand = it.itemInOffHand
            this.helmet = it.helmet
            this.chestplate = it.chestplate
            this.leggings = it.leggings
            this.boots = it.boots
        }
    }


    fun setFromPlayerCookies() {
        getSlotCookieFutures()
    }

    // ---------------------------------------------------------------
    private fun getSlotCookieFutures(slotList: List<String> = SLOT_KEYS) {
        val slotMap = createSlotFuturesMap(slotList)
        for (slot in slotMap) {
            val future: AwaitFuture = slot.value
            future.runTaskTimerAsynchronously(Yggdrasil.instance, 0, 20)
        }

    }

    // Create a new map of slots to futures
    private fun createSlotFuturesMap(slotList: List<String> = SLOT_KEYS): MutableMap<String, AwaitFuture> {
        val mapOfSlotToFuture = mutableMapOf<String, AwaitFuture>()
        for (key in slotList) {
            mapOfSlotToFuture[key] = createNewFuture(key)
        }
        return mapOfSlotToFuture
    }

    // create a new await future task
    private fun createNewFuture(name: String): AwaitFuture {
        return AwaitFuture(NamespacedKey(Yggdrasil.instance, name), player, 2)
    }

}
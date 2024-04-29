package me.shadowalzazel.yggdrasil.player_data

import me.shadowalzazel.yggdrasil.Yggdrasil
import me.shadowalzazel.yggdrasil.futures.CookieAwait
import me.shadowalzazel.yggdrasil.futures.CookieAwaitTask
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CookieInventory(val player: Player) {

    private val charset = Charsets.UTF_8

    val SLOT_MAP_INDEX: Map<String, Int> = mapOf(
        "slot_0" to 0,
        "helmet" to 36,
        "chestplate" to 37,
        "leggings" to 38,
        "boots" to 39,
        "offhand" to 40
    )

    var mainhand: ItemStack? = ItemStack(Material.AIR)
    var offhand: ItemStack? = ItemStack(Material.AIR)
    var helmet: ItemStack? = ItemStack(Material.AIR)
    var chestplate: ItemStack? = ItemStack(Material.AIR)
    var leggings: ItemStack? = ItemStack(Material.AIR)
    var boots: ItemStack? = ItemStack(Material.AIR)

    // ---------------------------------------------------------------

    // Called when creating from cookies
    fun createFromCookies() {
        getCookiesFromMap()
    }

    // Called when creating new inventory from player
    fun createFromPlayerInventory() {
        player.equipment.also { // TEMP
            this.mainhand = it.itemInMainHand
            this.offhand = it.itemInOffHand
            this.helmet = it.helmet
            this.chestplate = it.chestplate
            this.leggings = it.leggings
            this.boots = it.boots
        }
    }

    // ---------------------------------------------------------------
    // Get values from cookies [BLOCKS MAIN THREAD]
    private fun getCookiesFromMap(slotMap: Map<String, Int> = SLOT_MAP_INDEX) {
        val cookieMap = createMapSlotAwaits(slotMap)
        for (slot in cookieMap) {
            val futureCookie: CookieAwait = slot.value
            futureCookie.getFutureValue()
            val cookieValue = futureCookie.outcome
            println("Value for cookie [${slot.key}] (Byte): $cookieValue")
            println("Value for cookie [${slot.key}] (UTF-8): ${cookieValue.toString(charset)}")
        }

    }

    // Create a new map of slots to futures
    private fun createMapSlotAwaits(slotMap: Map<String, Int> = SLOT_MAP_INDEX): MutableMap<String, CookieAwait> {
        val mapSlotAwaits = mutableMapOf<String, CookieAwait>()
        for (slot in slotMap) {
            val nameKey = NamespacedKey(Yggdrasil.instance, slot.key)
            mapSlotAwaits[slot.key] = CookieAwait(nameKey, player)
        }
        return mapSlotAwaits
    }

    // create a new await future
    private fun createAwaitTasks(cookieAwait: CookieAwait): CookieAwaitTask {
        return CookieAwaitTask(cookieAwait, 2)
    }

}
package me.shadowalzazel.yggdrasil.player_data

import me.shadowalzazel.yggdrasil.Yggdrasil
import me.shadowalzazel.yggdrasil.futures.CookieAwait
import me.shadowalzazel.yggdrasil.futures.CookieAwaitTask
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CookieInventory(val player: Player) {

    val INVENTORY_SLOTS_INDEX: Map<String, Int> = mapOf(
        "slot_0" to 0,
        "helmet" to 39,
        "chestplate" to 38,
        "leggings" to 37,
        "boots" to 36,
        "offhand" to 40
    )

    val SLOT_ITEMS: MutableMap<String, ItemStack> = mutableMapOf()

    // ---------------------------------------------------------------
    // Called when creating from cookies
    fun createFromCookies() {
        getCookiesFromMap()
    }

    // Called when creating from player
    fun createFromPlayer() {
        for (slot in INVENTORY_SLOTS_INDEX) {
            val item = player.inventory.getItem(slot.value) ?: continue
            SLOT_ITEMS[slot.key] = item
        }
    }

    // ---------------------------------------------------------------
    // Get values from cookies [BLOCKS MAIN THREAD]
    private fun getCookiesFromMap(slotMap: Map<String, Int> = INVENTORY_SLOTS_INDEX) {
        val cookieMap = createCookieMap(slotMap)
        for (slot in cookieMap) {
            tryGetCookie(slot.value)
        }
    }

    private fun tryGetCookie(cookie: CookieAwait): ByteArray {
        val outcome = cookie.getFutureValue()
        // If took to long
        if (cookie.outcome.isEmpty() && !cookie.cookieFuture.isDone) {
            //println("Trying Asynchronously...")
            val awaitTask = CookieAwaitTask(cookie, 3)
            awaitTask.runTaskTimerAsynchronously(Yggdrasil.instance, 0, 5)
        }
        else {
            //println("Outcome (List): ${outcome.toList()}")
            //println("Outcome (UTF-8): ${outcome.toString(Charsets.UTF_8)}")
        }
        return outcome
    }

    // Create a new map of slots to futures
    fun createCookieMap(slotMap: Map<String, Int> = INVENTORY_SLOTS_INDEX): MutableMap<String, CookieAwait> {
        val mapSlotAwaits = mutableMapOf<String, CookieAwait>()
        for (slot in slotMap) {
            val nameKey = NamespacedKey(Yggdrasil.instance, slot.key)
            mapSlotAwaits[slot.key] = CookieAwait(nameKey, player)
        }
        return mapSlotAwaits
    }


}
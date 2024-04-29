package me.shadowalzazel.yggdrasil.futures

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import java.util.concurrent.CompletableFuture

@Suppress("UnstableApiUsage")
class AwaitFuture(val key: NamespacedKey, val player: Player, private val maxTries: Int) : BukkitRunnable() {

    var triesCounter = 0
    private val futureCookie: CompletableFuture<ByteArray>

    init {
        println("Init Future")
        futureCookie = player.retrieveCookie(key)
    }

    override fun run() {
        val pendingFuture = futureCookie
        // Max tries to fetch
        if (triesCounter > maxTries) {
            println("Cookie took too long to fetch")
            println("Pending Future: $pendingFuture")
            this.cancel()
            return
        }
        // Get future or add counter
        val value = futureCookie.get()
        if (futureCookie.isDone) {
            println("Cookie has been retrieved")
            println("Pending Future: $pendingFuture")
            println("Value (Byte): $value")
            println("Value (List): ${value.toList()}")
            this.cancel()
            return
        }
        else {
            triesCounter += 1
        }
    }

}
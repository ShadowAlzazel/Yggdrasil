package me.shadowalzazel.yggdrasil.futures

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@Suppress("UnstableApiUsage")
class CookieAwait(val key: NamespacedKey, val player: Player) {

    // Set Cookie future
    internal val cookieFuture = player.retrieveCookie(key)
    internal var outcome: ByteArray = byteArrayOf()

    fun getFutureValue(): ByteArray {
        // Try to get Future
        val value: ByteArray = try {
            cookieFuture.get(1000L, TimeUnit.MILLISECONDS)
        }
        catch (except: NullPointerException) {
            println("Cookie [$key] is Empty!")
            byteArrayOf()
        }
        catch (except: TimeoutException) {
            println("Cookie [$key] took to long to get!")
            byteArrayOf()
        }
        if (cookieFuture.isDone) {
            println("Cookie [$key] fetched! Value: $value")
            outcome = value
        }

        return outcome
    }

}
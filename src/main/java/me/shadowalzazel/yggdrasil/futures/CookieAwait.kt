package me.shadowalzazel.yggdrasil.futures

import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@Suppress("UnstableApiUsage")
class CookieAwait(val key: NamespacedKey, val player: Player, val maxTime: Long = 100L) {

    // Set Cookie future
    internal val cookieFuture = player.retrieveCookie(key)
    internal var outcome: ByteArray = byteArrayOf()

    fun getFutureValue(): ByteArray {
        // Try to get Future
        val value: ByteArray = try {
            cookieFuture.get(maxTime, TimeUnit.MILLISECONDS)
        }
        catch (except: NullPointerException) {
            println("Cookie Await [$key] is Empty!")
            byteArrayOf()
        }
        catch (except: TimeoutException) {
            println("Cookie Await [$key] took to long to get!")
            byteArrayOf()
        }
        if (cookieFuture.isDone) {
            println("Cookie Await [$key] fetched! Value: $value")
            outcome = value
        }

        return outcome
    }

}
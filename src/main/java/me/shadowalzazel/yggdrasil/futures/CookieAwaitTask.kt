package me.shadowalzazel.yggdrasil.futures

import org.bukkit.scheduler.BukkitRunnable

@Suppress("UnstableApiUsage")
class CookieAwaitTask(
    internal val cookieAwait: CookieAwait,
    internal val maxTries: Int):
    BukkitRunnable() {

    var outcome: ByteArray = byteArrayOf()
    private var triesCounter = 0

    override fun run() {
        // Max tries to fetch
        if (triesCounter > maxTries) {
            println("Cookie [${cookieAwait.key}] took too many tries to fetch")
            outcome = byteArrayOf()
            this.cancel()
            return
        }
        // Cancel if done or add to tries
        if (cookieAwait.cookieFuture.isDone) {
            println("Cookie [${cookieAwait.key} finished async with byte: ${cookieAwait.outcome}")
            outcome = cookieAwait.outcome
            this.cancel()
            return
        }
        else {
            triesCounter += 1
        }
        // Try to get futures
        outcome = cookieAwait.getFutureValue()
    }

}
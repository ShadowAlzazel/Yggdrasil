package me.shadowalzazel.yggdrasil

import me.shadowalzazel.yggdrasil.commands.admin.GetCookieString
import me.shadowalzazel.yggdrasil.commands.admin.SetCookieString
import me.shadowalzazel.yggdrasil.commands.admin.TransferPlayer
import me.shadowalzazel.yggdrasil.listeners.LoginManager
import org.bukkit.plugin.java.JavaPlugin

class Yggdrasil : JavaPlugin() {

    // Methods on Init
    companion object {
        lateinit var instance: Yggdrasil
    }

    init {
        instance = this
    }

    override fun onEnable() {
        // Start Timer
        val timerStart: Long = System.currentTimeMillis()
        // Config start up
        config.options().copyDefaults()
        saveConfig()

        // CREATE A COROUTINE THAT HANDLES COOKIE GETTING!!!!

        // Transfer
        if (!server.isAcceptingTransfers) {
            logger.info("Yggdrasil needs transfer enabled!")
            logger.info("Go to `server.properties file` and find `accepts-transfers` and set it to true.")
            return
        }
        else {
            logger.info("Yggdrasil accepting transfers authorized.")
        }

        // Register Events
        logger.info("Registering Events...")
        val eventListeners = listOf(
            LoginManager)
        eventListeners.forEach { server.pluginManager.registerEvents(it, this) }

        // Set Commands
        logger.info("Setting Commands...")
        val commandMap = mapOf(
            "get_cookie_string" to GetCookieString,
            "set_cookie_string" to SetCookieString,
            "transfer_player" to TransferPlayer)
        commandMap.forEach { getCommand(it.key)?.setExecutor(it.value) }

        // Hello World!
        val timeElapsed = (System.currentTimeMillis() - timerStart).div(1000.0)
        logger.info("Yggdrasil start up sequence in ($timeElapsed) seconds!")
    }

    override fun onDisable() {
        logger.info("Yggdrasil shutting down...")
    }

}
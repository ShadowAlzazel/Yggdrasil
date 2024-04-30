package me.shadowalzazel.yggdrasil.constants

import me.shadowalzazel.yggdrasil.Yggdrasil
import org.bukkit.NamespacedKey

sealed class CookieKeys(val key: NamespacedKey) {

    // Authorization
    data object AUTH_1: CookieKeys(NamespacedKey(Yggdrasil.instance, "auth_1"))

    data object TEST: CookieKeys(NamespacedKey(Yggdrasil.instance, "test"))
    data object COMMAND_STRING_TEST: CookieKeys(NamespacedKey(Yggdrasil.instance, "command_string_test"))

    // CREATE COOKIE KEYS

    // Spectator
    // Warning - cannot interact
    // Banned - Keys that servers can look for to disable logins
    // Permanently Banned - if assigned can not join any server

    // get from list from cloud
}
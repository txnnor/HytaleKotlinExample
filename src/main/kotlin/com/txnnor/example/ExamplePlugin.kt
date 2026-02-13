package com.txnnor.example

import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.txnnor.example.command.HelloCommand
import com.txnnor.example.event.listeners.PlayerJoinListener
import com.txnnor.example.event.systems.DamageBlockSystem

@Suppress("unused")
class ExamplePlugin(init: JavaPluginInit) : JavaPlugin(init) {

    // instantiate the player join listener.
    private val playerJoinListener = PlayerJoinListener(this)

    override fun setup() {
        // register event listeners
        eventRegistry.registerGlobal(PlayerConnectEvent::class.java, playerJoinListener::onConnect)
        eventRegistry.registerGlobal(PlayerReadyEvent::class.java, playerJoinListener::onReady)
        eventRegistry.registerGlobal(AddPlayerToWorldEvent::class.java, playerJoinListener::onAddToWorld)

        // register event systems
        entityStoreRegistry.registerSystem(DamageBlockSystem())

        //register commands
        commandRegistry.registerCommand(HelloCommand())
    }
}
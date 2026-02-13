package com.txnnor.example

import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent
import com.hypixel.hytale.server.core.plugin.JavaPlugin
import com.hypixel.hytale.server.core.plugin.JavaPluginInit
import com.hypixel.hytale.server.core.util.Config
import com.txnnor.example.command.DamageableBlocksPageCommand
import com.txnnor.example.command.HelloCommand
import com.txnnor.example.config.DamageBlockConfig
import com.txnnor.example.event.listeners.PlayerJoinListener
import com.txnnor.example.event.systems.DamageBlockSystem

@Suppress("unused")
class ExamplePlugin(init: JavaPluginInit) : JavaPlugin(init) {

    // instantiate the player join listener.
    private val playerJoinListener = PlayerJoinListener(this)

    // initialize the damage block config.
    val damageBlockConfig: Config<DamageBlockConfig> = this.withConfig("DamageableBlocks", DamageBlockConfig.CODEC)

    override fun setup() {
        // save default config values if non-existent.
        damageBlockConfig.save()

        // register event listeners
        eventRegistry.registerGlobal(PlayerConnectEvent::class.java, playerJoinListener::onConnect)
        eventRegistry.registerGlobal(PlayerReadyEvent::class.java, playerJoinListener::onReady)
        eventRegistry.registerGlobal(AddPlayerToWorldEvent::class.java, playerJoinListener::onAddToWorld)

        // register event systems
        entityStoreRegistry.registerSystem(DamageBlockSystem(this))

        //register commands
        commandRegistry.registerCommand(HelloCommand())
        commandRegistry.registerCommand(DamageableBlocksPageCommand(this))
    }
}
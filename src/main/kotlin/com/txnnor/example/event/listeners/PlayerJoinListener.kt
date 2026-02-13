package com.txnnor.example.event.listeners

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.event.events.player.AddPlayerToWorldEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerConnectEvent
import com.hypixel.hytale.server.core.event.events.player.PlayerReadyEvent
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.txnnor.example.ExamplePlugin
import java.awt.Color

class PlayerJoinListener(val plugin: ExamplePlugin) {

    fun onConnect(event: PlayerConnectEvent) {
        val name = event.holder.getComponent(PlayerRef.getComponentType())!!.username
        plugin.logger.atInfo().log("$name is connecting to the server!")
    }

    fun onReady(event: PlayerReadyEvent) {
        val player = event.player
        val world = event.player.world!!
        player.inventory.clear()
        val players = world.playerRefs
        for (p in players) {
            p.sendMessage(Message.join(
                Message.raw(player.displayName).color(Color.CYAN),
                Message.raw(" joined world ").color(Color.ORANGE),
                Message.raw(world.name).color(Color.GRAY),
                Message.raw("!").color(Color.ORANGE)
            ))
        }
    }

    fun onAddToWorld(event: AddPlayerToWorldEvent) {
        event.setBroadcastJoinMessage(false)
    }
}
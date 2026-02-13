package com.txnnor.example.event.listeners

import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.entity.entities.Player
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
        event.player.inventory.clear()
    }

    fun onAddToWorld(event: AddPlayerToWorldEvent) {
        val name = event.holder.getComponent(Player.getComponentType())!!.displayName
        event.setBroadcastJoinMessage(false)
        val players = event.world.playerRefs
        for (player in players) {
            player.sendMessage(Message.join(
                Message.raw(name).color(Color.ORANGE),
                Message.raw(" joined world ").color(Color.YELLOW),
                Message.raw(event.world.name).color(Color.RED),
                Message.raw("!").color(Color.YELLOW)
            ))
        }
    }
}
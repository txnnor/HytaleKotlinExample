package com.txnnor.example.command

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import java.awt.Color

class HelloCommand : AbstractPlayerCommand("hello", "A command to say hello!") {
    override fun execute(
        commandContext: CommandContext,
        store: Store<EntityStore>,
        ref: Ref<EntityStore>,
        playerRef: PlayerRef,
        world: World
    ) {
        commandContext.sendMessage(Message.join(
            Message.raw("Hello, ").color(Color.LIGHT_GRAY),
            Message.raw(playerRef.username).color(Color.ORANGE),
            Message.raw("!").color(Color.LIGHT_GRAY)
        ))
    }
}
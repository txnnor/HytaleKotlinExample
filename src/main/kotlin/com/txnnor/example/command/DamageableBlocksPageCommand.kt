package com.txnnor.example.command

import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.server.core.command.system.CommandContext
import com.hypixel.hytale.server.core.command.system.basecommands.AbstractPlayerCommand
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.World
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.txnnor.example.ExamplePlugin
import com.txnnor.example.ui.pages.DamageableBlocksPage

class DamageableBlocksPageCommand(val plugin: ExamplePlugin) : AbstractPlayerCommand("damageblock", "Opens a UI to add blocks you want to be able to damage!"){
    override fun execute(
        commandContext: CommandContext,
        store: Store<EntityStore>,
        ref: Ref<EntityStore>,
        playerRef: PlayerRef,
        world: World
    ) {
        val player = store.getComponent(ref, Player.getComponentType())!!
        player.pageManager.openCustomPage(ref, store, DamageableBlocksPage(plugin, playerRef))
    }
}
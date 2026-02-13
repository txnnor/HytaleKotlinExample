package com.txnnor.example.event.systems

import com.hypixel.hytale.component.ArchetypeChunk
import com.hypixel.hytale.component.CommandBuffer
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.component.query.Query
import com.hypixel.hytale.component.system.EntityEventSystem
import com.hypixel.hytale.protocol.packets.interface_.NotificationStyle
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.event.events.ecs.DamageBlockEvent
import com.hypixel.hytale.server.core.inventory.ItemStack
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.hypixel.hytale.server.core.util.NotificationUtil
import java.awt.Color


class DamageBlockSystem : EntityEventSystem<EntityStore, DamageBlockEvent>(DamageBlockEvent::class.java) {
    override fun handle(
        index: Int,
        archetypeChunk: ArchetypeChunk<EntityStore>,
        store: Store<EntityStore>,
        commandBuffer: CommandBuffer<EntityStore>,
        event: DamageBlockEvent
    ) {
        val ref = archetypeChunk.getReferenceTo(index)
        val playerRef = store.getComponent(ref, PlayerRef.getComponentType())!!
        event.isCancelled = true
        val icon = ItemStack("Armor_Prisma_Head", 1).toPacket()
        NotificationUtil.sendNotification(
            playerRef.packetHandler,
            Message.raw("Sorry, you can't break blocks!").color(Color.RED),
            Message.raw("You don't have permissions to do this!").color(Color.LIGHT_GRAY),
            icon,
            NotificationStyle.Warning
        )

    }

    override fun getQuery(): Query<EntityStore> {
        return PlayerRef.getComponentType()
    }
}
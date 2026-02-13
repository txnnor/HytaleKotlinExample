package com.txnnor.example.ui.pages

import com.hypixel.hytale.codec.Codec
import com.hypixel.hytale.codec.KeyedCodec
import com.hypixel.hytale.codec.builder.BuilderCodec
import com.hypixel.hytale.component.Ref
import com.hypixel.hytale.component.Store
import com.hypixel.hytale.protocol.packets.interface_.CustomPageLifetime
import com.hypixel.hytale.protocol.packets.interface_.CustomUIEventBindingType
import com.hypixel.hytale.protocol.packets.interface_.Page
import com.hypixel.hytale.server.core.Message
import com.hypixel.hytale.server.core.entity.entities.Player
import com.hypixel.hytale.server.core.entity.entities.player.pages.InteractiveCustomUIPage
import com.hypixel.hytale.server.core.ui.builder.EventData
import com.hypixel.hytale.server.core.ui.builder.UICommandBuilder
import com.hypixel.hytale.server.core.ui.builder.UIEventBuilder
import com.hypixel.hytale.server.core.universe.PlayerRef
import com.hypixel.hytale.server.core.universe.world.storage.EntityStore
import com.txnnor.example.ExamplePlugin
import kotlin.jvm.java

class DamageableBlocksPage(val plugin: ExamplePlugin, playerRef: PlayerRef) : InteractiveCustomUIPage<DamageableBlocksPage.AddBlockEventData>(
    playerRef,
    CustomPageLifetime.CanDismissOrCloseThroughInteraction,
    AddBlockEventData.CODEC
) {

    class AddBlockEventData {
        var blockId: String = ""

        companion object {
            val CODEC = BuilderCodec.builder(AddBlockEventData::class.java) { AddBlockEventData() }
                .append(
                    KeyedCodec("@BlockId", Codec.STRING),
                    { data: AddBlockEventData, v: String -> data.blockId = v },
                    { data: AddBlockEventData -> data.blockId }).add()
                .build()

        }
    }

    override fun build(
        ref: Ref<EntityStore>,
        cmd: UICommandBuilder,
        evt: UIEventBuilder,
        store: Store<EntityStore>
    ) {
        cmd.append("Pages/DamageableBlocksPage.ui")
        evt.addEventBinding(
            CustomUIEventBindingType.Activating,
            "#AddButton",
            EventData().append("@BlockId", "#BlockInput.Value")
        )
    }

    override fun handleDataEvent(
        ref: Ref<EntityStore>,
        store: Store<EntityStore>,
        data: AddBlockEventData
    ) {
        val player = store.getComponent(ref, Player.getComponentType())!!
        val config = plugin.damageBlockConfig.get()

        if (data.blockId.isNotEmpty()) {
            val updated = config.damageableBlocks + data.blockId
            config.damageableBlocks = updated
            plugin.damageBlockConfig.save()
        }

        player.sendMessage(Message.join(Message.translation("Added damageable block: "), Message.raw(data.blockId)))
        player.pageManager.setPage(ref, store, Page.None)
    }
}
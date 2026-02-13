package com.txnnor.example.config

import com.hypixel.hytale.codec.Codec
import com.hypixel.hytale.codec.KeyedCodec
import com.hypixel.hytale.codec.builder.BuilderCodec

class DamageBlockConfig {

    var damageableBlocks: Array<String> = arrayOf("Soil_Dirt", "Soil_Grass")

    companion object {
        val CODEC: BuilderCodec<DamageBlockConfig> =
            BuilderCodec.builder(DamageBlockConfig::class.java) { DamageBlockConfig() }
                .append(
                    KeyedCodec("DamageableBlocks", Codec.STRING_ARRAY),
                    { config: DamageBlockConfig, value: Array<String> -> config.damageableBlocks = value },
                    { config: DamageBlockConfig -> config.damageableBlocks }).add()
                .build()
    }
}
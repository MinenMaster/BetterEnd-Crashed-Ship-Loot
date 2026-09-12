package com.dominikmeister.betterendcrashedshiploot;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

@Mod(BetterEndCrashedShipLoot.MODID)
public class BetterEndCrashedShipLoot {
    public static final String MODID = "betterendcrashedshiploot";

    public static final Logger LOGGER = LogUtils.getLogger();

    public static final ResourceKey<LootTable> CRASHED_SHIP_LOOT = ResourceKey.create(
            Registries.LOOT_TABLE,
            ResourceLocation.fromNamespaceAndPath(MODID, "chests/crashed_ship"));

    public BetterEndCrashedShipLoot(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}

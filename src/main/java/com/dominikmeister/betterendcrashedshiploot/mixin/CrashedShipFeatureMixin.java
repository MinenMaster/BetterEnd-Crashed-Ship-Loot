package com.dominikmeister.betterendcrashedshiploot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import com.dominikmeister.betterendcrashedshiploot.BetterEndCrashedShipLoot;
import com.dominikmeister.betterendcrashedshiploot.Config;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import org.betterx.betterend.world.features.CrashedShipFeature;

@Mixin(value = CrashedShipFeature.class, remap = false)
public abstract class CrashedShipFeatureMixin {
    @Unique
    private static final org.slf4j.Logger betterendcrashedshiploot$LOGGER = BetterEndCrashedShipLoot.LOGGER;

    static {
        betterendcrashedshiploot$LOGGER.info(
                "CrashedShipFeatureMixin applied to {}, crashed ship chests will be filled from {}",
                CrashedShipFeature.class.getName(),
                BetterEndCrashedShipLoot.CRASHED_SHIP_LOOT.location());
    }

    @Inject(
            method = "place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z",
            at = @At("TAIL"),
            locals = LocalCapture.CAPTURE_FAILHARD)
    private void betterendcrashedshiploot$fillChests(
            FeaturePlaceContext<?> featureConfig,
            CallbackInfoReturnable<Boolean> cir,
            RandomSource random,
            BlockPos center,
            WorldGenLevel world,
            BoundingBox bounds) {
        if (!Config.SPEC.isLoaded() || !Config.ENABLED.get()) {
            return;
        }

        int filled = 0;
        for (BlockPos pos : BlockPos.betweenClosed(
                bounds.minX(), bounds.minY(), bounds.minZ(),
                bounds.maxX(), bounds.maxY(), bounds.maxZ())) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof RandomizableContainer container) {
                container.setLootTable(BetterEndCrashedShipLoot.CRASHED_SHIP_LOOT, random.nextLong());
                blockEntity.setChanged();
                filled++;
            }
        }

        if (filled == 0) {
            betterendcrashedshiploot$LOGGER.warn(
                    "Crashed ship placed at {} but no container was found in {} - no loot was added",
                    center, bounds);
        } else {
            betterendcrashedshiploot$LOGGER.debug(
                    "Filled {} container(s) in the crashed ship at {}", filled, center);
        }
    }
}

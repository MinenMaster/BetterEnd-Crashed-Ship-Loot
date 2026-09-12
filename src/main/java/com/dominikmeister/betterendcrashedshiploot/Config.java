package com.dominikmeister.betterendcrashedshiploot;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue ENABLED = BUILDER
            .comment("Whether to fill the chests of newly generated BetterEnd crashed ships.",
                    "Ships that have already generated are not affected either way.")
            .define("enabled", true);

    public static final ModConfigSpec SPEC = BUILDER.build();
}

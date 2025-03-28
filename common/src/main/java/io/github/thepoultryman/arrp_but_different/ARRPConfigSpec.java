package io.github.thepoultryman.arrp_but_different;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ARRPConfigSpec {
    public static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue DUMP = BUILDER
            .comment("Whether to dump the generated resource packs to the dump directory. Used for debugging.")
            .define("dump", false);
    private static final ModConfigSpec.ConfigValue<String> DUMP_DIRECTORY = BUILDER
            .comment("The directory where the resource packs are dumped to.")
            .define("dumpDirectory", System.getProperty("java.io.tmpdir") + "/arrp");

    public static final ModConfigSpec CONFIG = BUILDER.build();

    public static void load() {
        ARRPConfig.dump = DUMP.getAsBoolean();
        ARRPConfig.dumpDirectory = DUMP_DIRECTORY.get();
    }
}

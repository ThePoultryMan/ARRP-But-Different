package io.github.thepoultryman.arrp_neoforge;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = ARRPForNeoForge.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ARRPForNeoForgeConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.BooleanValue DUMP = BUILDER
            .comment("Whether to dump the generated resource packs to the dump directory. Used for debugging.")
            .define("dump", false);
    private static final ModConfigSpec.ConfigValue<String> DUMP_DIRECTORY = BUILDER
            .comment("The directory where the resource packs are dumped to.")
            .define("dumpDirectory", System.getProperty("java.io.tmpdir") + "/arrp");

    static final ModConfigSpec CONFIG = BUILDER.build();

    public static boolean dump;
    public static String dumpDirectory;

    @SubscribeEvent
    private static void onLoad(ModConfigEvent event) {
        dump = DUMP.getAsBoolean();
        dumpDirectory = DUMP_DIRECTORY.get();
    }
}

package io.github.thepoultryman.arrp_but_different.fabric;

import io.github.thepoultryman.arrp_but_different.util.AddOnlyList;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.packs.PackResources;

import java.util.function.Function;

public interface ARRPEvent {
    Function<ARRPEvent[], ARRPEvent> CALLBACK = (listeners) -> (resourcePacks) -> {
        AddOnlyList<PackResources> packs = new AddOnlyList<>(resourcePacks);
        for (ARRPEvent event : listeners) {
            event.insert(packs);
        }
    };

    Event<ARRPEvent> BEFORE_VANILLA = EventFactory.createArrayBacked(ARRPEvent.class, CALLBACK);
    Event<ARRPEvent> BEFORE_USER = EventFactory.createArrayBacked(ARRPEvent.class, CALLBACK);
    Event<ARRPEvent> AFTER_VANILLA = EventFactory.createArrayBacked(ARRPEvent.class, CALLBACK);
    Event<ARRPEvent> BETWEEN_VANILLA_AND_MODS = EventFactory.createArrayBacked(ARRPEvent.class, CALLBACK);
    Event<ARRPEvent> BETWEEN_MODS_AND_USER = EventFactory.createArrayBacked(ARRPEvent.class, CALLBACK);

    void insert(AddOnlyList<PackResources> resourcePacks);
}

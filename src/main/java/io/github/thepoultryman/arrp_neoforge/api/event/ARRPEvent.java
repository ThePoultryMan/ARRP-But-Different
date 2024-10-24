package io.github.thepoultryman.arrp_neoforge.api.event;

import net.minecraft.server.packs.repository.Pack;
import net.neoforged.bus.api.Event;

import java.util.ArrayList;
import java.util.List;

public abstract class ARRPEvent extends Event {
    private final List<Pack> packs = new ArrayList<>();

    public void addPack(Pack pack) {
        this.packs.add(pack);
    }

    public List<Pack> getPacks() {
        return this.packs;
    }

    public static class BeforeVanillaEvent extends ARRPEvent {}
    public static class BetweenVanillaAndModsEvent extends ARRPEvent {}
    public static class BetweenModsAndUserEvent extends ARRPEvent {}
    public static class BeforeUserEvent extends ARRPEvent {}
    public static class AfterVanillaEvent extends ARRPEvent {}
}

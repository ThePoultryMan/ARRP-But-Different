package io.github.thepoultryman.arrp_but_different.api.event;

import net.minecraft.server.packs.repository.Pack;

import java.util.List;

public interface ARRPNeoForgeEvent {
    void insert(List<Pack> packs);
}

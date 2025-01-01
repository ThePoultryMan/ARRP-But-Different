package io.github.thepoultryman.arrp_but_different.json.loot;

import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;

import java.util.ArrayList;
import java.util.List;

public class JLootTable extends BaseCloneable<JLootTable> {
    private final String type;
    private List<JPool> pools;

    public JLootTable(String type) {
        this.type = type;
    }

    public JLootTable pool(JPool pool) {
        if (this.pools == null) {
            this.pools = new ArrayList<>();
        }
        this.pools.add(pool);
        return this;
    }
}

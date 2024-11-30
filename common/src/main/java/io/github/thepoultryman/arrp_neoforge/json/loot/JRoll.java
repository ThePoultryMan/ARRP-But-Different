package io.github.thepoultryman.arrp_neoforge.json.loot;

import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

public class JRoll extends BaseCloneable<JRoll> {
    private final int min;
    private final int max;

    public JRoll(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

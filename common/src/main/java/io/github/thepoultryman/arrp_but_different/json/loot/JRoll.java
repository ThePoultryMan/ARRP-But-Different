package io.github.thepoultryman.arrp_but_different.json.loot;

import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;

public class JRoll extends BaseCloneable<JRoll> {
    private final int min;
    private final int max;

    public JRoll(int min, int max) {
        this.min = min;
        this.max = max;
    }
}

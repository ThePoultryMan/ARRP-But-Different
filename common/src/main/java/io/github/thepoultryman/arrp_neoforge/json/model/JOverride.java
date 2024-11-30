package io.github.thepoultryman.arrp_neoforge.json.model;

import io.github.thepoultryman.arrp_neoforge.json.JCondition;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

public class JOverride extends BaseCloneable<JOverride> {
    public final JCondition predicate;
    public final String model;

    public JOverride(JCondition condition, String model) {
        this.predicate = condition;
        this.model = model;
    }
}

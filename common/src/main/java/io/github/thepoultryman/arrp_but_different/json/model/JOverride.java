package io.github.thepoultryman.arrp_but_different.json.model;

import io.github.thepoultryman.arrp_but_different.json.JCondition;
import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;

public class JOverride extends BaseCloneable<JOverride> {
    public final JCondition predicate;
    public final String model;

    public JOverride(JCondition condition, String model) {
        this.predicate = condition;
        this.model = model;
    }
}

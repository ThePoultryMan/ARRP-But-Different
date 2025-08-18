package io.github.thepoultryman.arrp_but_different.json.model;

import io.github.thepoultryman.arrp_but_different.json.JCondition;

public class JOverride {
    public final JCondition predicate;
    public final String model;

    public JOverride(JCondition condition, String model) {
        this.predicate = condition;
        this.model = model;
    }
}

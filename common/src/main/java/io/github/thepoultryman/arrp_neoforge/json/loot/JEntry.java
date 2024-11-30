package io.github.thepoultryman.arrp_neoforge.json.loot;

import io.github.thepoultryman.arrp_neoforge.json.JCondition;
import io.github.thepoultryman.arrp_neoforge.util.BaseCloneable;

import java.util.ArrayList;
import java.util.List;

public class JEntry extends BaseCloneable<JEntry> {
    private String type;
    private String name;
    private List<JEntry> children;
    private Boolean expand;
    private List<JFunction> functions;
    private List<JCondition> conditions;
    private Integer weight;
    private Integer quality;

    public JEntry type(String type) {
        this.type = type;
        return this;
    }

    public JEntry name(String name) {
        this.name = name;
        return this;
    }

    public JEntry child(JEntry child) {
        if (this == child) {
            throw new IllegalArgumentException("An entry can't be added as its own child.");
        }
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
        return this;
    }

    public JEntry expand(boolean expand) {
        this.expand = expand;
        return this;
    }

    public JEntry function(JFunction function) {
        if (this.functions == null) {
            this.functions = new ArrayList<>();
        }
        this.functions.add(function);
        return this;
    }

    public JEntry function(String function) {
        return this.function(new JFunction(function));
    }

    public JEntry condition(JCondition condition) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    public JEntry condition(String condition) {
        return this.condition(new JCondition(condition));
    }

    public JEntry weight(int weight) {
        this.weight = weight;
        return this;
    }

    public JEntry quality(int quality) {
        this.quality = quality;
        return this;
    }
}

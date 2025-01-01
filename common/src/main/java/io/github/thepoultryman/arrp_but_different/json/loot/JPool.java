package io.github.thepoultryman.arrp_but_different.json.loot;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_but_different.json.JCondition;
import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JPool extends BaseCloneable<JPool> {
    private List<JCondition> conditions;
    private List<JFunction> functions;
    private List<JEntry> entries;
    private Integer rolls;
    private JRoll roll;
    @SerializedName("bonus_rolls")
    private Integer bonusRolls;
    @SerializedName("bonus_roll")
    private JRoll bonusRoll;

    public JPool entry(JEntry entry) {
        if (this.entries == null) {
            this.entries = new ArrayList<>();
        }
        this.entries.add(entry);
        return this;
    }

    public JPool condition(JCondition condition) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    public JPool function(JFunction function) {
        if (this.functions == null) {
            this.functions = new ArrayList<>();
        }
        this.functions.add(function);
        return this;
    }

    public JPool rolls(int rolls) {
        this.rolls = rolls;
        return this;
    }

    public JPool rolls(JRoll roll) {
        this.roll = roll;
        return this;
    }

    public JPool bonusRolls(int bonusRolls) {
        this.bonusRolls = bonusRolls;
        return this;
    }

    public JPool bonusRolls(JRoll bonusRoll) {
        this.bonusRoll = bonusRoll;
        return this;
    }

    public static class Serializer implements JsonSerializer<JPool> {
        @Override
        public JsonElement serialize(JPool src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            if (src.conditions != null) {
                jsonObject.add("conditions", context.serialize(src.conditions));
            }
            if (src.functions != null) {
                jsonObject.add("functions", context.serialize(src.functions));
            }
            if (src.entries != null) {
                jsonObject.add("entries", context.serialize(src.entries));
            }
            if (src.rolls != null) {
                jsonObject.addProperty("rolls", src.rolls);
            }
            if (src.roll != null) {
                jsonObject.add("rolls", context.serialize(src.roll));
            }
            if (src.bonusRolls != null) {
                jsonObject.add("bonus_rolls", context.serialize(src.bonusRolls));
            }
            if (src.bonusRoll != null) {
                jsonObject.add("bonus_rolls", context.serialize(src.bonusRoll));
            }
            return jsonObject;
        }
    }
}

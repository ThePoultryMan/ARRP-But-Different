package io.github.thepoultryman.arrp_but_different.json.loot;

import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import io.github.thepoultryman.arrp_but_different.json.JsonUtil;
import io.github.thepoultryman.arrp_but_different.util.BaseCloneable;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntries;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.*;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JPool extends BaseCloneable<JPool> {
    private List<LootItemCondition> conditions;
    private List<LootItemFunction> functions;
    private List<LootPoolEntryContainer> entries;
    private NumberProvider rolls;
    @SerializedName("bonus_rolls")
    private NumberProvider bonusRolls;

    public JPool entry(LootPoolEntryContainer entry) {
        if (this.entries == null) {
            this.entries = new ArrayList<>();
        }
        this.entries.add(entry);
        return this;
    }

    public JPool condition(LootItemCondition condition) {
        if (this.conditions == null) {
            this.conditions = new ArrayList<>();
        }
        this.conditions.add(condition);
        return this;
    }

    public JPool function(LootItemFunction function) {
        if (this.functions == null) {
            this.functions = new ArrayList<>();
        }
        this.functions.add(function);
        return this;
    }

    public JPool rolls(NumberProvider rolls) {
        this.rolls = rolls;
        return this;
    }

    public JPool bonusRolls(NumberProvider bonusRolls) {
        this.bonusRolls = bonusRolls;
        return this;
    }

    private static Codec<NumberProvider> getNumberProviderCodec(@NotNull NumberProvider numberProvider) {
        return (Codec<NumberProvider>) switch (numberProvider) {
            case BinomialDistributionGenerator ignored -> BinomialDistributionGenerator.CODEC.codec();
            case ConstantValue ignored -> ConstantValue.CODEC.codec();
            case EnchantmentLevelProvider ignored -> EnchantmentLevelProvider.CODEC.codec();
            case ScoreboardValue ignored -> ScoreboardValue.CODEC.codec();
            case StorageValue ignored -> ScoreboardValue.CODEC.codec();
            case UniformGenerator ignored -> UniformGenerator.CODEC.codec();
            default -> throw new IllegalStateException("Unexpected value: " + numberProvider);
        };
    }

    public static class Serializer implements JsonSerializer<JPool> {
        @Override
        public JsonElement serialize(JPool src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonObject = new JsonObject();
            if (src.conditions != null) {
                jsonObject.add("conditions", JsonUtil.serializeCodecList(src.conditions, LootItemCondition.TYPED_CODEC));
            }
            if (src.functions != null) {
                jsonObject.add("functions", JsonUtil.serializeCodecList(src.functions, LootItemFunctions.TYPED_CODEC));
            }
            if (src.entries != null) {
                JsonArray entries = new JsonArray(src.entries.size());
                for (LootPoolEntryContainer entry : src.entries) {
                    entries.add(LootPoolEntries.CODEC.encodeStart(JsonOps.INSTANCE, entry).getOrThrow());
                }
                jsonObject.add("entries", entries);
            }
            if (src.rolls != null) {
                jsonObject.add("rolls", getNumberProviderCodec(src.rolls).encodeStart(JsonOps.INSTANCE, src.rolls).getOrThrow());
            }
            if (src.bonusRolls != null) {
                jsonObject.add("bonus_rolls", getNumberProviderCodec(src.bonusRolls).encodeStart(JsonOps.INSTANCE, src.rolls).getOrThrow());
            }
            return jsonObject;
        }
    }
}

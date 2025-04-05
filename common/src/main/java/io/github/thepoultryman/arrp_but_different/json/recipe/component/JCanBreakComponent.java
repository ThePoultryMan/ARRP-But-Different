package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.annotations.SerializedName;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JCanBreakComponent extends AbstractJComponent {
    private final List<JPredicate> predicates = new ArrayList<>();
    @SerializedName("show_in_tooltip")
    private boolean showInTooltip;

    public static class JPredicate {
        private final List<ResourceLocation> blocks = new ArrayList<>();
        private final HashMap<String, String> state = new HashMap<>();
        private String nbt;

        public JPredicate block(ResourceLocation block) {
            this.blocks.add(block);
            return this;
        }

        public JPredicate state(String state, String value) {
            this.state.put(state, value);
            return this;
        }

        public JPredicate nbt(String nbt) {
            this.nbt = nbt;
            return this;
        }
    }
}

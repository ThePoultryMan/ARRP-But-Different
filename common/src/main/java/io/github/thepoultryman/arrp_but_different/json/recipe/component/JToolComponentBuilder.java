package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.minecraft.world.item.component.Tool;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JToolComponentBuilder extends AbstractJComponent {
    private Optional<Tool> tool;
    private final List<Tool.Rule> rules = new ArrayList<>();
    private float defaultMiningSpeed;
    private int damagePerBlock;
    private boolean canDestroyBlocksInCreative;

    /**
     * Creates a {@link Tool} through the builder pattern. There shouldn't be a reason why you need
     * to call {@link #build()}, but it is public if you need to do so.
     * <br />
     * If you're going to be using all the fields on {@link Tool}, you are encouraged to use the
     * constructor overload that takes a {@link Tool} instance. This will merge the rules from
     * {@link JToolComponentBuilder} and the provided {@link Tool} together, while keeping all the other values
     * from the {@link Tool}.
     */
    public JToolComponentBuilder() {
        this.defaultMiningSpeed = 1.0f;
        this.damagePerBlock = 1;
        this.canDestroyBlocksInCreative = true;
    }

    /**
     * This overload should only be called if you want to merge {@link Tool.Rule}s provided to
     * {@link JToolComponentBuilder} with an existing {@link Tool}.
     * @param tool The {@link Tool} rules will be merged with.
     */
    public JToolComponentBuilder(@NotNull Tool tool) {
        this.tool = Optional.of(tool);
    }

    public JToolComponentBuilder defaultMiningSpeed(float speed) {
        this.defaultMiningSpeed = speed;
        return this;
    }

    public JToolComponentBuilder damagePerBlock(int damage) {
        this.damagePerBlock = damage;
        return this;
    }

    public JToolComponentBuilder canDestroyBlocksInCreative(boolean canDestroy) {
        this.canDestroyBlocksInCreative = canDestroy;
        return this;
    }

    public JToolComponentBuilder rule(Tool.Rule rule) {
        this.rules.add(rule);
        return this;
    }

    public Tool build() {
        this.tool.ifPresent((tool) -> tool.rules().addAll(this.rules));
        return this.tool.orElse(new Tool(this.rules, this.defaultMiningSpeed, this.damagePerBlock, this.canDestroyBlocksInCreative));
    }

    public static class Serializer implements JsonSerializer<JToolComponentBuilder> {
        @Override
        public JsonElement serialize(JToolComponentBuilder src, Type type, JsonSerializationContext context) {
            return context.serialize(src.build());
        }
    }
}

package io.github.thepoultryman.arrp_but_different.json.recipe.component;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import io.github.thepoultryman.arrp_but_different.json.recipe.JResult;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JBundleContentsComponent extends AbstractJComponent {
    // JResult happens to have the same fields that this field takes, so we're just going to use it
    // until it differs.
    private final List<JResult> contents = new ArrayList<>();

    public JBundleContentsComponent content(JResult content) {
        this.contents.add(content);
        return this;
    }

    public static class Serializer implements JsonSerializer<JBundleContentsComponent> {
        @Override
        public JsonElement serialize(JBundleContentsComponent src, Type type, JsonSerializationContext context) {
            return context.serialize(src.contents);
        }
    }
}

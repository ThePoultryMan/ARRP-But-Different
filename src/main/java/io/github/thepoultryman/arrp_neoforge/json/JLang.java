package io.github.thepoultryman.arrp_neoforge.json;

import java.util.HashMap;
import java.util.Map;

public class JLang implements Cloneable {
    private final Map<String, String> lang = new HashMap<>();

    public static JLang lang() {
        return new JLang();
    }

    @Override
    public JLang clone() {
        try {
            return (JLang) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}

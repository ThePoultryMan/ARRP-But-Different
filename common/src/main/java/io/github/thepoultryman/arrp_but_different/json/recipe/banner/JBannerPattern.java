package io.github.thepoultryman.arrp_but_different.json.recipe.banner;

import com.google.gson.annotations.SerializedName;
import io.github.thepoultryman.arrp_but_different.json.recipe.component.AbstractJComponent;

public class JBannerPattern extends AbstractJComponent {
    private Color color;
    private JBannerPatternType pattern;

    public enum Color {
        @SerializedName("white")
        White,
        @SerializedName("orange")
        Orange,
        @SerializedName("magenta")
        Magenta,
        @SerializedName("light_blue")
        LightBlue,
        @SerializedName("yellow")
        Yellow,
        @SerializedName("lime")
        Lime,
        @SerializedName("pink")
        Pink,
        @SerializedName("gray")
        Gray,
        @SerializedName("light_gray")
        LightGray,
        @SerializedName("cyan")
        Cyan,
        @SerializedName("purple")
        Purple,
        @SerializedName("blue")
        Blue,
        @SerializedName("brown")
        Brown,
        @SerializedName("green")
        Green,
        @SerializedName("red")
        Red,
        @SerializedName("black")
        Black,
    }
}

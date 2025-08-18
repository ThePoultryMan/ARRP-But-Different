package io.github.thepoultryman.arrp_but_different.json.animation;

public class JFrame {
    private final int index;
    private Integer time;

    public JFrame(int index) {
        this.index = index;
    }

    public JFrame time(int time) {
        this.time = time;
        return this;
    }
}

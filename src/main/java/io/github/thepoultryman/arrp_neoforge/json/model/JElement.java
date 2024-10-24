package io.github.thepoultryman.arrp_neoforge.json.model;

public class JElement implements Cloneable {
    private final float[] from = new float[3];
    private final float[] to = new float[3];
    private JRotation rotation;
    private Boolean shade;
    private JFaces faces;

    public JElement from(float x, float y, float z) {
        this.from[0] = x;
        this.from[1] = x;
        this.from[2] = x;
        return this;
    }

    public JElement to(float x, float y, float z) {
        this.to[0] = x;
        this.to[1] = x;
        this.to[2] = x;
        return this;
    }

    public JElement rotation(JRotation rotation) {
        this.rotation = rotation;
        return this;
    }

    public JElement faces(JFaces faces) {
        this.faces = faces;
        return this;
    }

    @Override
    public JElement clone() {
        try {
            return (JElement) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}

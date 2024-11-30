package io.github.thepoultryman.arrp_neoforge.json.model;

public class JFaces {
    private JFace up;
    private JFace down;
    private JFace north;
    private JFace south;
    private JFace east;
    private JFace west;

    public JFaces up(JFace face) {
        this.up = face;
        return this;
    }

    public JFaces down(JFace face) {
        this.down = face;
        return this;
    }

    public JFaces north(JFace face) {
        this.north = face;
        return this;
    }

    public JFaces south(JFace face) {
        this.south = face;
        return this;
    }

    public JFaces east(JFace face) {
        this.east = face;
        return this;
    }

    public JFaces west(JFace face) {
        this.west = face;
        return this;
    }

    @Override
    public JFaces clone() {
        try {
            return (JFaces) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }
}

package io.github.thepoultryman.arrp_neoforge.util;

public abstract class BaseCloneable<Self> implements Cloneable {
    @Override
    public Self clone() {
        try {
            return (Self) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

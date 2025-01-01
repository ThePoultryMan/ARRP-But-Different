package io.github.thepoultryman.arrp_but_different.util;

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

package io.github.thepoultryman.arrp_but_different.util;

import java.util.AbstractList;
import java.util.List;

public class AddOnlyList<T> extends AbstractList<T> {
    private final List<T> list;

    public AddOnlyList(List<T> list) {
        this.list = list;
    }

    @Override
    public T get(int i) {
        return this.list.get(i);
    }

    @Override
    public boolean add(T element) {
        return this.list.add(element);
    }

    @Override
    public int size() {
        return this.list.size();
    }
}

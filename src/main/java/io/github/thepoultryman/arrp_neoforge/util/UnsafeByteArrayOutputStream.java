package io.github.thepoultryman.arrp_neoforge.util;

import java.io.OutputStream;
import java.util.Arrays;

public class UnsafeByteArrayOutputStream extends OutputStream implements AutoCloseable {
    private byte[] buf;
    private int index;

    public UnsafeByteArrayOutputStream(int size) throws IllegalAccessException {
        if (size < 0) {
            throw new IllegalAccessException("Negative initial size: " + size);
        }
        this.buf = new byte[size];
    }

    public byte[] getBytes() {
        return Arrays.copyOf(this.buf, this.index);
    }

    private void ensureCapacity(int minCapacity) {
        int length = this.buf.length;
        if (minCapacity > length) {
            int size = Math.max(minCapacity, length / 2 + length);
            this.buf = Arrays.copyOf(this.buf, size);
        }
    }

    @Override
    public void write(int i) {
        this.ensureCapacity(this.index + 1);
        this.buf[this.index++] = (byte) i;
    }
}

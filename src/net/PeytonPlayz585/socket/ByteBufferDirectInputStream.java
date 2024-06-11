package net.PeytonPlayz585.socket;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public class ByteBufferDirectInputStream extends InputStream {
    private ByteBuffer buf;
    public ByteBufferDirectInputStream(ByteBuffer b) {
        this.buf = b;
    }

    @Override
    public int read() throws IOException {
        return buf.remaining() > 0 ? ((int) buf.get() & 0xFF) : -1;
    }

    @Override
    public int available() {
        return buf.remaining();
    }
}
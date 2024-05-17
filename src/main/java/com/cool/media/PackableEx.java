package com.cool.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}

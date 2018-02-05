package org.yoqu.fish.common.codec;


public class Header {

    public Header(short magic, byte version, byte extend, Long seq, Integer size) {
        this.magic = magic;
        this.version = version;
        this.extend = extend;
        this.seq = seq;
        this.size = size;
    }

    private short magic;

    private byte version;

    private byte extend;

    private Long seq;

    private Integer size;

    public short getMagic() {
        return magic;
    }

    public void setMagic(short magic) {
        this.magic = magic;
    }

    public byte getVersion() {
        return version;
    }

    public void setVersion(byte version) {
        this.version = version;
    }

    public byte getExtend() {
        return extend;
    }

    public void setExtend(byte extend) {
        this.extend = extend;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }


}

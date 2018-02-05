package org.yoqu.fish.common.codec;

public class Message<T> {

    private Header header;

    private T content;

    public Message(Header header, T content) {
        this.header = header;
        this.content = content;
    }

    public Message() {
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}

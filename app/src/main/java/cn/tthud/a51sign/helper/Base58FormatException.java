package cn.tthud.a51sign.helper;

@SuppressWarnings("serial")
public class Base58FormatException extends RuntimeException {
    public Base58FormatException() {
        super();
    }

    public Base58FormatException(String message) {
        super(message);
    }
}
package cn.tthud.a51sign.helper;

/**
 * Created by d.romantsov on 24.02.2016.
 */
public class Bip39FormatExceptipn extends RuntimeException {
    public Bip39FormatExceptipn(String message, Throwable cause) {
        super(message, cause);
    }

    public Bip39FormatExceptipn(String message) {
        super(message);
    }
}

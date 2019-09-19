package com.merchant.framework;

/**
 * @author liyu
 * @date 2019-09-18 13:33
 */
public class UnIntelligibleException extends RuntimeException {

    public UnIntelligibleException(String message) {
        super(message);
    }

    public UnIntelligibleException() {
        super("I have no idea what you are talking about");
    }
}

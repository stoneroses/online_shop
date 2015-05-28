package com.wang.michael.online_shop.exception;

public class EmailJobNotFound extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -3417270272896064161L;

    public EmailJobNotFound(String message) {
        super(message);
    }

    public EmailJobNotFound() {
        super();
    }
}

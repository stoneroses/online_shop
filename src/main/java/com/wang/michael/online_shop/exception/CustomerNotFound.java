package com.wang.michael.online_shop.exception;

public class CustomerNotFound extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2350266978940602247L;

    public CustomerNotFound(String message) {
        super(message);
    }

    public CustomerNotFound() {
        super();
    }
}

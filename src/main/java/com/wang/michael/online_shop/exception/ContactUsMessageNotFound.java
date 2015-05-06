package com.wang.michael.online_shop.exception;

public class ContactUsMessageNotFound extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1359169352303943690L;

    public ContactUsMessageNotFound(String message) {
        super(message);
    }

    public ContactUsMessageNotFound() {
        super();
    }
}

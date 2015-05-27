package com.wang.michael.online_shop.exception;

public class CustomerGroupNotFound extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2836647751075203976L;

    public CustomerGroupNotFound(String message) {
        super(message);
    }

    public CustomerGroupNotFound() {
        super();
    }
}

package com.wang.michael.online_shop.exception;

public class RoleNotFound extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7190706246128243037L;

    public RoleNotFound(String message) {
        super(message);
    }

    public RoleNotFound() {
        super();
    }
}

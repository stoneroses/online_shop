package com.wang.michael.online_shop.exception;

public class PermissionNotFound extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -6441680814108906681L;

    public PermissionNotFound(String message) {
        super(message);
    }

    public PermissionNotFound() {
        super();
    }
}

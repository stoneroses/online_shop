package com.wang.michael.online_shop.exception;

public class UserNotFound extends Exception {

    /**
   * 
   */
    private static final long serialVersionUID = 2576449960765846533L;

    public UserNotFound(String message) {
        super(message);
    }

    public UserNotFound() {
        super();
    }
}

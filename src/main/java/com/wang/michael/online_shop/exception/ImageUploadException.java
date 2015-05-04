package com.wang.michael.online_shop.exception;

public class ImageUploadException extends Exception {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2021403197877597823L;

    public ImageUploadException(String message, Exception e) {
        super(message, e);
    }

    public ImageUploadException(String message) {
        super(message);
    }

}

package com.wang.michael.online_shop;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class OutputPassword {

    public static void main(String[] args) {

        String userNameC = "cnlancer@gmail.com";
        String userNameM = "michael.wang.brisbane@gmail.com";
        String password = "password";
        String encryptPassword = new Sha256Hash(password).toString();
        String encryptPasswordWithSaltC = new Sha256Hash(password, userNameC).toString();
        String encryptPasswordWithSaltM = new Sha256Hash(password, userNameM).toString();

        System.out.println(encryptPassword);
        System.out.println(encryptPasswordWithSaltC);
        System.out.println(encryptPasswordWithSaltM);

    }

}

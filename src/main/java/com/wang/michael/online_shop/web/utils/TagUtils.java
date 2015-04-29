package com.wang.michael.online_shop.web.utils;

import java.util.List;

public class TagUtils {

    public static boolean contains(List<?> list, Object object) {
        if (list == null) {
            return false;
        }
        return list.contains(object);
    }
}

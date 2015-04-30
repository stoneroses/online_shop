package com.wang.michael.online_shop.web.utils;

import java.util.Collection;

public class TagUtils {

    public static boolean contains(Collection<?> list, Object object) {
        if (list == null) {
            return false;
        }
        return list.contains(object);
    }
}

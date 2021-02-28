package com.library.error;

import com.library.entity.User;

import java.util.HashMap;
import java.util.Map;

public class Error {

    private static final Map<User,String> map = new HashMap<>();

    public static void setMessage (User user, String message) {
        map.put(user, message);
    }

    public static String getMessage (User user) {
        String message = map.get(user);
        map.remove(user);
        return message;
    }
}
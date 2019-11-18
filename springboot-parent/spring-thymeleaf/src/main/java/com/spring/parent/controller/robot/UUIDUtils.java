package com.spring.parent.controller.robot;

import java.util.UUID;

public class UUIDUtils {
    public static String generateId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

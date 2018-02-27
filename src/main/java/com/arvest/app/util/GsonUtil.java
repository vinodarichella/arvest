package com.arvest.app.util;

import com.google.gson.Gson;

public class GsonUtil {

    public static <T> T fromJson(String json, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(json, t);
    }
}

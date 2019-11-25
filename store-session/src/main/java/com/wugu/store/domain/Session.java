package com.wugu.store.domain;

import java.util.HashMap;

public class Session {

    private HashMap<String, String> hashMap;

    public Session() {
        hashMap = new HashMap<>(10);
    }

    public Session(int initialCapacity) {
        hashMap = new HashMap<>(initialCapacity);
    }

    public void setAttribute(String key, String value) {
        hashMap.put(key, value);
    }

    public String getAttribute(String key) {
        return hashMap.get(key);
    }
}

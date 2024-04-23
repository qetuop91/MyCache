package com.feliks.core;

import java.util.LinkedHashMap;
import java.util.Map;

public class MyCache<K, V> extends LinkedHashMap<K, V> {
    private int size;
    private Object lock;

    public MyCache(int size, Object lock) {
        super((int) (size * 1.4f), 0.75f, true);
        this.size = size;
        if (lock != null) {
            this.lock = lock;
        } else {
            this.lock = this.getClass();
        }
    }

    public MyCache setLock(Object lock) {
        this.lock = lock;
        return this;
    }

    public MyCache setSize(int size) {
        this.size = size;
        return this;
    }


    /**
     * 重写LinkedHashMap的removeEldestEntry方法
     * 在Put的时候判断如果为true就会删除最老的
     *
     * @param eldest
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > size;
    }

    /**
     * 当其他线程试图访问被synchronized修饰的代码块时会被阻塞，
     * 只有当前拿到锁的进程可以访问代码块
     *
     * @param key
     */
    public Object getValue(K key) {
        synchronized (lock) {
            return get(key);
        }
    }

    /**
     * @param key
     * @param value
     */
    public void putValue(K key, V value) {
        synchronized (lock) {
            put(key, value);
        }
    }

    public boolean removeValue(K key) {
        synchronized (lock) {
            Object remove = remove(key);
            return remove != null;
        }
    }

    public boolean removeAll() {
        synchronized (lock) {
            clear();
            return true;
        }
    }
}

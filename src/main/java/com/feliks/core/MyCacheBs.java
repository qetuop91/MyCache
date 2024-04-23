package com.feliks.core;

public class MyCacheBs<K, V> {
    private int size = Integer.MAX_VALUE;
    private Object lock;

    private MyCacheBs() {
    }

    /**
     * 创建对象实例
     *
     * @return
     */
    public static MyCacheBs newBuilder() {
        return new MyCacheBs();
    }

    /**
     * 设置大小限制
     *
     * @param size
     * @return
     */
    public MyCacheBs setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * @param lock
     * @return
     */
    public MyCacheBs setLock(Object lock) {
        this.lock = lock;
        return this;
    }

    public MyCache build() {
        MyCache<K, V> myCache = new MyCache<>(size, lock);
        return myCache;
    }
}

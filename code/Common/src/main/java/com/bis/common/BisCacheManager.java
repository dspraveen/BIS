package com.bis.common;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class BisCacheManager {

    private CacheManager cacheManager;
    private Cache bisCache;

    public BisCacheManager() {
        try {
            this.cacheManager = new CacheManager(new ClassPathResource("ehcache.xml").getInputStream());
            bisCache = cacheManager.getCache("bis_cache");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean cached(Object key) {
        return bisCache.isKeyInCache(key);
    }

    public boolean notCached(Object key) {
        return !cached(key);
    }

    public Object get(Object key) {
        if (notCached(key)) return null;
        return bisCache.get(key).getObjectValue();
    }

    public void cache(Object key, Object value) {
        if(cached(key)) remove(key);
        bisCache.put(new Element(key, value));
    }

    public void remove(Object key) {
        bisCache.remove(key);
    }

}

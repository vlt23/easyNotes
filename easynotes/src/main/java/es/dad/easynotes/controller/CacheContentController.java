package es.dad.easynotes.controller;

import com.hazelcast.spring.cache.HazelcastCache;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CacheContentController {

    @Autowired
    private CacheManager cacheManager;

    // Debug only
    @RequestMapping(value = "/cache")
    public Map<Object, Object> getCacheContent() {
        HazelcastCacheManager hazelcastCacheManager = (HazelcastCacheManager) cacheManager;
        HazelcastCache hazelcastCache = (HazelcastCache) hazelcastCacheManager.getCache("apuntes");
        return hazelcastCache.getNativeCache();
    }

}

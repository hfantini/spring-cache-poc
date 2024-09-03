package com.henriquefantini.cacheboot.service;

import com.henriquefantini.cacheboot.model.Data;
import com.henriquefantini.cacheboot.repository.DataRepository;
import com.henriquefantini.cacheboot.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private DataRepository dataRepository;

    /**
     * This is the most basic example around how to work with cached information and simple key generation strategy
     */
    @Cacheable(value = "data", key = "#id")
    public List<Data> getData(String id) throws Exception
    {
        Thread.sleep(RandomUtil.getRandomNumberBetween(1000, 5000));
        return dataRepository.getData(RandomUtil.getRandomNumberBetween(2, 64));
    }

    /**
     * This example was made to show how to work with custom interceptors for key generation
     */
    @Cacheable(value = "data", keyGenerator = "customKeyGenerator")
    public List<Data> getDataInterceptor(String id, String authorization) throws Exception
    {
        Thread.sleep(RandomUtil.getRandomNumberBetween(1000, 5000));
        return dataRepository.getData(RandomUtil.getRandomNumberBetween(2, 64));
    }

    /**
     * This example was made to show how to work with a different cache with custom TTL
     */
    @Cacheable(value = "custom", key = "#id")
    public List<Data> getDataCustomTTL(String id) throws Exception
    {
        Thread.sleep(RandomUtil.getRandomNumberBetween(1000, 5000));
        return dataRepository.getData(RandomUtil.getRandomNumberBetween(2, 64));
    }

    /**
     * This is the most basic example around how to work with cached information manually
     */
    public List<Data> getManualData(String id) throws Exception
    {
        Cache.ValueWrapper valueWrapper = cacheManager.getCache("data").get(id);

        if(valueWrapper != null) {
            return (List<Data>) valueWrapper.get();
        }

        Thread.sleep(RandomUtil.getRandomNumberBetween(1000, 5000));
        return dataRepository.getData(RandomUtil.getRandomNumberBetween(2, 64));
    }

    /**
     * This is an example about how could we manually evict the entire cache
     */
    public void clearEntireCache() {
        cacheManager.getCache("data").clear();
    }

    /**
     * This is an example about how could we manually evict a cache entry
     */
    public void clearCacheByKey(String key) {
        cacheManager.getCache("data").evict(key);
    }
}

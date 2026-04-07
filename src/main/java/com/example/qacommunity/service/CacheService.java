package com.example.qacommunity.service;

import com.fasterxml.jackson.core.type.TypeReference;

public interface CacheService {
    void set(String key, Object value, long expireSeconds);
    
    <T> T get(String key, Class<T> clazz);
    
    <T> T get(String key, TypeReference<T> typeRef);
    
    void delete(String key);
    
    boolean exists(String key);
}

package com.example.qacommunity.service.impl;

import com.example.qacommunity.service.CacheService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private final ObjectMapper objectMapper;

    public CacheServiceImpl() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Override
    public void set(String key, Object value, long expireSeconds) {
        try {
            String json = objectMapper.writeValueAsString(value);
            redisTemplate.opsForValue().set(key, json, expireSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException("缓存设置失败", e);
        }
    }

    @Override
    public <T> T get(String key, Class<T> clazz) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if (value == null) {
                return null;
            }
            String json = value.toString();
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public <T> T get(String key, TypeReference<T> typeRef) {
        try {
            Object value = redisTemplate.opsForValue().get(key);
            if (value == null) {
                return null;
            }
            String json = value.toString();
            return objectMapper.readValue(json, typeRef);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}

package com.parse.steam.repo.redis;

import com.parse.steam.dtos.redis.OuterDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisRepoImpl implements RedisRepo {
    @Value(value = "${market}")
    private String redisKey;

    private final RedisTemplate<String, Object> redisTemplate;

    private HashOperations<String, Long, OuterDto> hashOperations;

    @Autowired
    public RedisRepoImpl(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        this.hashOperations = redisTemplate.opsForHash();
    }

    @Override
    public boolean save(OuterDto emp) {
        hashOperations.put(redisKey, emp.getId(), emp);
        return true;
    }

    @Override
    public boolean delete(String key, Long id) {
        hashOperations.delete(redisKey, id);
        return true;
    }
}

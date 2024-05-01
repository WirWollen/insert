package com.parse.steam.repo.redis;

import com.parse.steam.dtos.redis.OuterDto;


public interface RedisRepo {
    boolean save(OuterDto emp);

    boolean delete(String key, Long id);
}

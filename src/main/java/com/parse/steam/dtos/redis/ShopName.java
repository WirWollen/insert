package com.parse.steam.dtos.redis;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public enum ShopName {
    CITILINK("CITILINK", 100L, "monitor"),
    DNS("DNS", 200L, "monitor");

    private final String name;
    private final Long categoryId;
    private final String categoryName;
}

package com.parse.steam.dtos.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OuterDto {
    private Long id;
    private ShopName shopName;
    private String url;
}

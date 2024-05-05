package com.parse.steam.dtos.redis;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OuterDto implements Serializable {
    private Long id;
    private Long categoryId;
    private String shopName;
    private String url;
}

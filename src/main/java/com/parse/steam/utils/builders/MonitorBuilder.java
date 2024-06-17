package com.parse.steam.utils.builders;

import com.parse.steam.dtos.stat.PredicateDto;
import com.parse.steam.dtos.stat.StatLowestPriceDto;
import com.parse.steam.entities.MonitorEntity;

public class MonitorBuilder {
    public static MonitorEntity buildUpdatedMonitorEntity(MonitorEntity a, MonitorEntity b) {
        a.setName(b.getName());
        a.setNits(b.getNits());
        a.setDiagonal(b.getDiagonal());
        a.setHz(b.getHz());
        a.setGtg(b.getGtg());
        a.setHdmi(b.getHdmi());
        a.setDp(b.getDp());
        a.setVga(b.getVga());
        a.setSize(b.getSize());
        a.setBrand(b.getBrand());
        a.setMatrix(b.getMatrix());
        a.setArchived(b.getArchived());
        return a;
    }

    public static PredicateDto buildPredicateDto(String status, String marketName, Long price, String url) {
        PredicateDto dto = new PredicateDto();
        StatLowestPriceDto stat = new StatLowestPriceDto();
        stat.setUrl(url);
        stat.setMarketName(marketName);
        stat.setPrice(price);
        dto.setStatus(status);
        dto.setStatLowestPriceDto(stat);
        return dto;
    }
}

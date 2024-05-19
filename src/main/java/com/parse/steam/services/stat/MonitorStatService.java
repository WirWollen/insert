package com.parse.steam.services.stat;

import com.parse.steam.repo.parsed.MonitorStatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonitorStatService {
    private final MonitorStatRepo monitorStatRepo;


}

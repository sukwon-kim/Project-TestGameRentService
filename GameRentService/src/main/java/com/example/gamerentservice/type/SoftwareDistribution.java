package com.example.gamerentservice.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SoftwareDistribution {
    PLAYSTATIONSHOP("플레이스테이션네트워크"),
    NINTENTDOESHOP("닌텐도샵"),
    MICROSOFTSHOP("마이크로소프트샵"),
    STEAM("스팀");

    private final String description;
}

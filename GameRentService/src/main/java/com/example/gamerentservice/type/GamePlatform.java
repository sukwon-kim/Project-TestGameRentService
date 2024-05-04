package com.example.gamerentservice.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GamePlatform {

    PLAYSTATION4("플레이스테이션4"),
    PLAYSTATION5("플레이스테이션5"),
    XBOXONE("엑스박스원"),
    XBOXSERIESX("엑스박스시리즈X"),
    NINTENDOSWITCH("닌텐도스위치"),
    MICROSOFTWINDOWS("마이크로소프트 윈도우");

    private final String description;
}

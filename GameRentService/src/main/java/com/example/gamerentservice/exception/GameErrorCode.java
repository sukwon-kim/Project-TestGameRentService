package com.example.gamerentservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum GameErrorCode {
    WRONG_PLATFORM("잘못된 플랫폼입니다"),
    DUPLICATE_GAMETITLE("중복하는 게임이 있습니다"),
    NO_GAME("해당하는 게임이 없습니다");

    private final String description;
}

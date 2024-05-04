package com.example.gamerentservice.exception;

import lombok.Getter;

@Getter
public class GameErrorException extends RuntimeException{
    private GameErrorCode gameErrorCode;
    private String errorMessage;

    public GameErrorException(GameErrorCode errorCode) {
        super(errorCode.getDescription());
        this.gameErrorCode = errorCode;
        this.errorMessage = errorCode.getDescription();
    }

    public GameErrorException(GameErrorCode errorCode, String message) {
        super(errorCode.getDescription());
        this.gameErrorCode = errorCode;
        this.errorMessage = message;
    }
}

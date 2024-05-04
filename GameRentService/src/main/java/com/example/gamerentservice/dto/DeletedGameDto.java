package com.example.gamerentservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeletedGameDto {
    //타이틀
    private String gameTitle;

    //개발사
    private String developer;

    //유통사
    private String publisher;
}

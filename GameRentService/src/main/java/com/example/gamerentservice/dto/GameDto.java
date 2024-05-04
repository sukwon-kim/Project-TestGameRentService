package com.example.gamerentservice.dto;

import com.example.gamerentservice.converter.BooleanToYN;
import com.example.gamerentservice.entity.GameEntity;
import com.example.gamerentservice.type.GamePlatform;
import com.example.gamerentservice.type.SoftwareDistribution;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDto {

    //타이틀
    private String gameTitle;

    //개발사
    private String developer;

    //유통사
    private String publisher;

    //플랫폼
    @Enumerated(EnumType.STRING)
    private GamePlatform gamePlatform;

    //소프트웨어 유통망
    @Enumerated(EnumType.STRING)
    private SoftwareDistribution softwareDistribution;

    //조회수
    private Long view;

    // 대출여부
    @Convert(converter = BooleanToYN.class)
    private boolean isloaned;

    public static GameDto toDto(GameEntity entity) {
        return GameDto.builder()
                .developer(entity.getDeveloper())
                .publisher(entity.getPublisher())
                .gamePlatform(entity.getGamePlatform())
                .softwareDistribution(entity.getSoftwareDistribution())
                .view(entity.getView())
                .isloaned(entity.isIsloaned())
                .build();
    }
}

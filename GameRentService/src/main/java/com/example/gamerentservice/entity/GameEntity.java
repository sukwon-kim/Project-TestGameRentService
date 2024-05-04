package com.example.gamerentservice.entity;

import com.example.gamerentservice.converter.BooleanToYN;
import com.example.gamerentservice.dto.GameDto;
import com.example.gamerentservice.type.GamePlatform;
import com.example.gamerentservice.type.SoftwareDistribution;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Builder
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    //출시일
    @CreatedDate
    private LocalDateTime releaseDate;

    //조회수
    private Long view;

    // 대출여부
    @Convert(converter = BooleanToYN.class)
    private boolean isloaned;

    public GameEntity toEntity(GameDto gameDto){
        return GameEntity.builder()
                .developer(gameDto.getDeveloper())
                .publisher(gameDto.getPublisher())
                .gamePlatform(gameDto.getGamePlatform())
                .softwareDistribution(gameDto.getSoftwareDistribution())
                .view(gameDto.getView())
                .isloaned(gameDto.isIsloaned())
                .build();
    }

}

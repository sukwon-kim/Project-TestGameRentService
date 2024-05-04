package com.example.gamerentservice.service;

import com.example.gamerentservice.dto.EditGameDto;
import com.example.gamerentservice.dto.GameDto;
import com.example.gamerentservice.entity.DeletedGameEntity;
import com.example.gamerentservice.entity.GameEntity;
import com.example.gamerentservice.exception.GameErrorCode;
import com.example.gamerentservice.exception.GameErrorException;
import com.example.gamerentservice.repository.DeletedGameRentRepository;
import com.example.gamerentservice.repository.GameRentRepository;
import com.example.gamerentservice.type.GamePlatform;
import com.example.gamerentservice.type.SoftwareDistribution;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GameRentService {
    private final GameRentRepository gameRentRepository;
    private final DeletedGameRentRepository deletedGameRentRepository;

    @Transactional
    public GameDto createGame(GameDto request) {
        validateGameRequest(request);
        GameEntity gameEntity = GameEntity.builder()
                .gameTitle(request.getGameTitle())
                .developer(request.getDeveloper())
                .publisher(request.getPublisher())
                .gamePlatform(request.getGamePlatform())
                .softwareDistribution(request.getSoftwareDistribution())
                .view(0L)
                .isloaned(request.isIsloaned())
                .build();
        gameRentRepository.save(gameEntity);

        return GameDto.toDto(gameEntity);
    }

    private void validateGameRequest(GameDto request) {
        //플랫폼 검증
        validateGamePlatform(request.getGamePlatform(), request.getSoftwareDistribution());

        //중복 체크
        gameRentRepository.findByGameTitle(request.getGameTitle())
                .ifPresent(game -> new GameErrorException(GameErrorCode.DUPLICATE_GAMETITLE));
    }

    private void validateGamePlatform(GamePlatform gamePlatform, SoftwareDistribution softwareDistribution) {
        if (softwareDistribution == SoftwareDistribution.PLAYSTATIONSHOP &&
                ((gamePlatform == GamePlatform.MICROSOFTWINDOWS )|| (gamePlatform == GamePlatform.NINTENDOSWITCH)
                        || (gamePlatform == GamePlatform.XBOXONE) || (gamePlatform == GamePlatform.XBOXSERIESX))) {
            throw new GameErrorException(GameErrorCode.WRONG_PLATFORM);
        }
        if (softwareDistribution == SoftwareDistribution.NINTENTDOESHOP &&
                ((gamePlatform == GamePlatform.MICROSOFTWINDOWS )|| (gamePlatform == GamePlatform.PLAYSTATION4)
                        || (gamePlatform == GamePlatform.XBOXONE ) || (gamePlatform == GamePlatform.XBOXSERIESX)
                        || (gamePlatform == GamePlatform.PLAYSTATION5)) ){
            throw new GameErrorException(GameErrorCode.WRONG_PLATFORM);
        }
        if (softwareDistribution == SoftwareDistribution.MICROSOFTSHOP &&
                ((gamePlatform == GamePlatform.PLAYSTATION4) || (gamePlatform == GamePlatform.NINTENDOSWITCH)
                        || (gamePlatform == GamePlatform.XBOXONE) || (gamePlatform == GamePlatform.XBOXSERIESX)
                        || (gamePlatform == GamePlatform.PLAYSTATION5)) ){
            throw new GameErrorException(GameErrorCode.WRONG_PLATFORM);
        }
    }

    public List<GameDto> showAllGame() {
        return gameRentRepository.findAll().stream().map(GameDto::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public GameDto showGame(String gameTitle) {
        return gameRentRepository.findByGameTitle(gameTitle).map(GameDto::toDto)
                .orElseThrow(() -> new GameErrorException(GameErrorCode.NO_GAME));
    }

    public GameDto editGame(String gameTitle, EditGameDto editgameDto) {
        GameEntity gameEntity = gameRentRepository.findByGameTitle(gameTitle)
                .orElseThrow(() -> new GameErrorException(GameErrorCode.NO_GAME));

        gameEntity.setGamePlatform(editgameDto.getGamePlatform());
        gameEntity.setSoftwareDistribution(editgameDto.getSoftwareDistribution());

        return GameDto.toDto(gameEntity);
    }

    public GameDto delteGame(String gameTitle) {
        GameEntity gameEntity = gameRentRepository.findByGameTitle(gameTitle)
                .orElseThrow(RuntimeException::new);

        DeletedGameEntity deletedGameEntity = DeletedGameEntity.builder()
                .gameTitle(gameEntity.getGameTitle())
                .developer(gameEntity.getDeveloper())
                .publisher(gameEntity.getPublisher())
                .build();
        gameRentRepository.delete(gameEntity);
        deletedGameRentRepository.save(deletedGameEntity);
        return GameDto.toDto(gameEntity);
    }

    @Transactional
    public void updateView(String gameTitle) {
        gameRentRepository.updateView(gameTitle);
    }
}

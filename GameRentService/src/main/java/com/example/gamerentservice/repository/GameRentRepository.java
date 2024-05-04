package com.example.gamerentservice.repository;

import com.example.gamerentservice.dto.GameDto;
import com.example.gamerentservice.entity.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GameRentRepository extends JpaRepository<GameEntity, Long> {
    Optional<GameEntity> findByGameTitle(String title);

    @Modifying
    @Query(value = "update GameEntity b set b.view = b.view + 1 where b.gameTitle = :title")
    void updateView(@Param("title") String gameTitle);
}

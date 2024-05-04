package com.example.gamerentservice.repository;

import com.example.gamerentservice.entity.DeletedGameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeletedGameRentRepository extends JpaRepository<DeletedGameEntity, Long> {
}

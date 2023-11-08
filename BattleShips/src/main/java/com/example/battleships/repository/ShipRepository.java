package com.example.battleships.repository;

import com.example.battleships.model.entity.Ship;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<Ship, Long> {
    Optional<Ship> findByName(String name);

    List<Ship> findAllByUserId(long ownerId);

    List<Ship> findAllByUserIdNot(long ownerId);

    List<Ship> findByOrderByNameAscHealthAscPowerAsc();
}

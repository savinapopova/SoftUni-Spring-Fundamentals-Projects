package com.example.battleships.service;

import com.example.battleships.model.dto.CreateShipDTO;
import com.example.battleships.model.dto.ShipDTO;

import java.util.List;

public interface ShipService {
    boolean create(CreateShipDTO createShipDTO);

    List<ShipDTO> getShipsOwnedBy(long ownerId);

    List<ShipDTO> getShipsNotOwnedBy(long ownerId);

    List<ShipDTO> getAllSorted();
}

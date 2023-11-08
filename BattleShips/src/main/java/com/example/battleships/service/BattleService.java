package com.example.battleships.service;

import com.example.battleships.model.dto.StartBattleDTO;

public interface BattleService {
    void attack(StartBattleDTO startBattleDTO);
}

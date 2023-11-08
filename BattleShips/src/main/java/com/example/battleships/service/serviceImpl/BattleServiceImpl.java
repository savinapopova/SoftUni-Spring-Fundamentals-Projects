package com.example.battleships.service.serviceImpl;

import com.example.battleships.model.dto.StartBattleDTO;
import com.example.battleships.model.entity.Ship;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.service.BattleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BattleServiceImpl implements BattleService {

    private ShipRepository shipRepository;

    @Autowired
    public BattleServiceImpl(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    @Override
    public void attack(StartBattleDTO startBattleDTO) {
        Optional<Ship> optAttacker = shipRepository.findById(startBattleDTO.getAttackerId());
        Optional<Ship> optDefender = shipRepository.findById(startBattleDTO.getDefenderId());

        if (optAttacker.isEmpty() || optDefender.isEmpty()) {
            throw new NoSuchElementException();
        }

        Ship attacker = optAttacker.get();
        Ship defender = optDefender.get();

        long newDefenderHealth = defender.getHealth() - attacker.getPower();

        if (newDefenderHealth <= 0) {
            shipRepository.delete(defender);
        } else {
            defender.setHealth(newDefenderHealth);
            shipRepository.save(defender);
        }
    }
}

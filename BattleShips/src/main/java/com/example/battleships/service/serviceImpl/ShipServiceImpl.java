package com.example.battleships.service.serviceImpl;

import com.example.battleships.LoggedUser;
import com.example.battleships.model.dto.CreateShipDTO;
import com.example.battleships.model.dto.ShipDTO;
import com.example.battleships.model.entity.Category;
import com.example.battleships.model.entity.Ship;
import com.example.battleships.model.entity.User;
import com.example.battleships.model.enums.CategoryName;
import com.example.battleships.repository.CategoryRepository;
import com.example.battleships.repository.ShipRepository;
import com.example.battleships.repository.UserRepository;
import com.example.battleships.service.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShipServiceImpl implements ShipService {

    private ShipRepository shipRepository;

    private CategoryRepository categoryRepository;

    private UserRepository userRepository;

    private LoggedUser loggedUser;

    @Autowired
    public ShipServiceImpl(ShipRepository shipRepository, CategoryRepository categoryRepository, UserRepository userRepository, LoggedUser loggedUser) {
        this.shipRepository = shipRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.loggedUser = loggedUser;
    }

    @Override
    public boolean create(CreateShipDTO createShipDTO) {
        Optional<Ship> optionalShip = shipRepository
                .findByName(createShipDTO.getName());

        if (optionalShip.isPresent()) {
            return false;
        }

        CategoryName categoryName = CategoryName.valueOf(createShipDTO.getCategory()); {

        };

        Category category = categoryRepository
                .findByName(categoryName);

        Optional<User> owner = userRepository.findById(loggedUser.getId());

        Ship ship = new Ship(createShipDTO.getName(),
                createShipDTO.getHealth(),
                createShipDTO.getPower(),
                createShipDTO.getCreated(),
                category,
                owner.get());
        shipRepository.save(ship);
        return true;
    }

    @Override
    public List<ShipDTO> getShipsOwnedBy(long ownerId) {
       return shipRepository.findAllByUserId(ownerId)
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getShipsNotOwnedBy(long ownerId) {
        return shipRepository.findAllByUserIdNot(ownerId)
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipDTO> getAllSorted() {
        return shipRepository.findByOrderByNameAscHealthAscPowerAsc()
                .stream()
                .map(ShipDTO::new)
                .collect(Collectors.toList());
    }


}

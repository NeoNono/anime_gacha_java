package server.controllers;

import org.springframework.web.bind.annotation.RestController;
import server.database.OwnedCharacterRepository;
import server.service.PlayerService;
import server.service.RouletteService;

@RestController
public class PlayerController {

private final PlayerService playerService;

private final RouletteService rouletteService;

    public PlayerController(PlayerService playerService, RouletteService rouletteService) {
        this.playerService = playerService;
        this.rouletteService = rouletteService;
    }


}

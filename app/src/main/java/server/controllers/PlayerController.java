package server.controllers;

import commons.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import server.database.OwnedCharacterRepository;
import server.service.PlayerService;
import server.service.RouletteService;

import java.util.List;

@RestController
public class PlayerController {

private final PlayerService playerService;

private final RouletteService rouletteService;

    public PlayerController(PlayerService playerService, RouletteService rouletteService) {
        this.playerService = playerService;
        this.rouletteService = rouletteService;
    }

    @GetMapping(path = { "", "/" })
    public List<Player> getAll() {
        return playerService.getPlayers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") long id) {
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }
}

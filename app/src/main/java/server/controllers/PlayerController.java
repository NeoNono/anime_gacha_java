package server.controllers;

import commons.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import server.database.OwnedCharacterRepository;
import server.service.PlayerService;
import server.service.RouletteService;

import java.util.List;

@RestController
public class PlayerController {

private final PlayerService playerService;

private final SimpMessagingTemplate simpmessagingTemplate;
private final RouletteService rouletteService;

    public PlayerController(PlayerService playerService, SimpMessagingTemplate simpmessagingTemplate, RouletteService rouletteService) {
        this.playerService = playerService;
        this.simpmessagingTemplate = simpmessagingTemplate;
        this.rouletteService = rouletteService;
    }

    @GetMapping(path = "/players")
    public List<Player> getAll() {
        return playerService.getPlayers();
    }

    @GetMapping(path = "/players/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") long id) {
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @DeleteMapping(path = "player/{id}")
    public ResponseEntity<Player> deleteById(@PathVariable("id") long id) {
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        Player dp = playerService.getPlayerById(id);
        playerService.deletePlayer(id);
        simpmessagingTemplate.convertAndSend(String.valueOf(dp.id), dp);
        return ResponseEntity.ok(dp);
    }

    @PostMapping("player/{id}")
    public ResponseEntity<Player> createPlayer(@PathVariable ("id") Long id) {
        Player player = playerService.createPlayer(id);
        return ResponseEntity.ok(player);
    }

    @GetMapping("player/{id}/balance")
    public ResponseEntity<Integer> getPlayerBalance(@PathVariable long id) {
        int balance = playerService.getPlayerBalance(id);
        return ResponseEntity.ok(balance);
    }


}

package server.controllers;

import commons.OwnedCharacter;
import commons.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import server.database.OwnedCharacterRepository;
import server.service.CharacterService;
import server.service.PlayerService;
import server.service.RouletteService;

import java.util.List;

@RestController
public class PlayerController {

private final PlayerService playerService;

private final RouletteService rouletteService;
private final CharacterService characterService;

    public PlayerController(PlayerService playerService,  RouletteService rouletteService, CharacterService characterService) {
        this.playerService = playerService;
        this.rouletteService = rouletteService;
        this.characterService = characterService;
    }

    @GetMapping(path = "/players/{id}")
    public ResponseEntity<Player> getById(@PathVariable("id") long id) {
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @DeleteMapping(path = "/players/{id}")
    public ResponseEntity<Player> deleteById(@PathVariable long id) {
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        Player dp = playerService.getPlayerById(id);
        playerService.deletePlayer(id);
        return ResponseEntity.ok(dp);
    }

    @PostMapping("/players")
    public ResponseEntity<Player> createPlayer() {
        Player player = playerService.createPlayer();
        return ResponseEntity.ok(player);
    }

    @GetMapping("/players/{id}/balance")
    public ResponseEntity<Integer> getPlayerBalance(@PathVariable long id) {
        int balance = playerService.getPlayerBalance(id);
        return ResponseEntity.ok(balance);
    }

    @GetMapping("/players/{id}/characters")
    public ResponseEntity<List<OwnedCharacter>> ownedCharacters(@PathVariable long id){
        if (id < 0 || !playerService.exists(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(playerService.getPlayerCharacters(id));
    }



}

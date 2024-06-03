package server.controllers;

import commons.Character;
import commons.OwnedCharacter;
import commons.OwnedCharacterId;
import commons.Player;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import server.database.OwnedCharacterRepository;
import server.service.CharacterService;
import server.service.PlayerService;
import server.service.RouletteService;

import java.util.List;
import java.util.NoSuchElementException;

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

    @PatchMapping("/players/{id}/characters/{code}/sell")
    public ResponseEntity<List<OwnedCharacter>> sellOwnedCharacter(@PathVariable long id, @PathVariable long code){
        if (id < 0 || code < 0) return ResponseEntity.badRequest().build();

        try {
            return ResponseEntity.ok(playerService.sellGivenCharacter(id, code));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/players/{id}/characters/{code}/upgrade")
    public ResponseEntity<OwnedCharacter> upgradeCharacter(@PathVariable long id, @PathVariable long code){
        if (id < 0 || code < 0) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok(playerService.upgradeOwnedCharacter(id, code));
        }
        catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
        catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }

}

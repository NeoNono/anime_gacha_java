package server.controllers;

import commons.Fight;
import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.CharacterService;
import server.service.FightService;
import server.service.PlayerService;
import server.service.RouletteService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class FightController {

    private final RouletteService rouletteService;

    private final PlayerService playerService;

    private final CharacterService characterService;

    private final FightService fightService;

    public FightController(RouletteService rouletteService, PlayerService playerService,
                           CharacterService characterService, FightService fightService) {
        this.rouletteService = rouletteService;
        this.playerService = playerService;
        this.characterService = characterService;
        this.fightService = fightService;
    }

    @PostMapping("/fights/seed")
    public void seedDatabase(){
        this.fightService.seedDatabase();
    }

    @GetMapping("/fights")
    public ResponseEntity<List<Fight>> possibleFights(){
        List<Fight> fights = fightService.getPossibleFights();
        return ResponseEntity.ok(fights);
    }

    @PostMapping("/players/{id}/characters/{code}/fights/{fightId}")
    public ResponseEntity<Player> fightAnEnemy(@PathVariable long id, @PathVariable long code, @PathVariable long fightId){
        if (id < 0 || code < 0) return ResponseEntity.badRequest().build();
        try {
            return ResponseEntity.ok(fightService.fightEnemy(id, code, fightId));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }

    }
}

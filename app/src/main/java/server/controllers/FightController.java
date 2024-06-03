package server.controllers;

import commons.Fight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.CharacterService;
import server.service.FightService;
import server.service.PlayerService;
import server.service.RouletteService;

import java.util.List;
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

    @GetMapping("/fights")
    public ResponseEntity<List<Fight>> possibleFights(){
        List<Fight> fights = fightService.getFights();
        return ResponseEntity.ok(fights);
    }
}

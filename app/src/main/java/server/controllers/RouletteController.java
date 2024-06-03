package server.controllers;

import commons.Character;
import commons.Fight;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import server.database.OwnedCharacterRepository;
import server.service.CharacterService;
import server.service.PlayerService;
import server.service.RouletteService;

import java.util.List;

@RestController
public class RouletteController {

    private final RouletteService rouletteService;

    private final PlayerService playerService;

    private final CharacterService characterService;

    public RouletteController(RouletteService rouletteService, PlayerService playerService, CharacterService characterService) {
        this.rouletteService = rouletteService;
        this.playerService = playerService;
        this.characterService = characterService;
    }

    @GetMapping(path = {"", "/"})
    public ResponseEntity<String> getMethod() {
        String welcomeMessage = """
                <p>
                Welcome to GachaGirls! <br>
                Here is the API map: <br>
                POST /players <br>
                    Create a new player <br>
                    <br>
                etc...<br>
                </p>
                """;

        return ResponseEntity.ok(welcomeMessage);

    }

    @GetMapping("/fights")
    public ResponseEntity<List<Fight>>possibleFights(){
        List<Fight> fights = rouletteService.getFights();
        return ResponseEntity.ok(fights);
    }
}
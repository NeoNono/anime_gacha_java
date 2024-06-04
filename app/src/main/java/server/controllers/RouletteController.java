package server.controllers;

import commons.Character;
import commons.Fight;
import commons.OwnedCharacter;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.database.OwnedCharacterRepository;
import server.service.CharacterService;
import server.service.FightService;
import server.service.PlayerService;
import server.service.RouletteService;

import java.util.List;
import java.util.Optional;

@RestController
public class RouletteController {

    private final RouletteService rouletteService;

    private final PlayerService playerService;

    private final CharacterService characterService;

    private final FightService fightService;

    public RouletteController(RouletteService rouletteService, PlayerService playerService,
                              CharacterService characterService, FightService fightService) {
        this.rouletteService = rouletteService;
        this.playerService = playerService;
        this.characterService = characterService;
        this.fightService = fightService;
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

    @PostMapping("/roulette/pulls")
    public ResponseEntity<List<OwnedCharacter>> pullRoulette(@RequestParam long playerId){
        if (playerId < 0 || !playerService.exists(playerId)) {
            return ResponseEntity.badRequest().build();
        }
        Optional<List<OwnedCharacter>> ownedCharacters = this.rouletteService.pullRoulette(playerId);
        if (ownedCharacters.isEmpty())
            return ResponseEntity.noContent().build();

        return ResponseEntity.ok(ownedCharacters.get());
    }


}
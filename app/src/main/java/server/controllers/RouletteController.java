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
                <strong>Player options</strong><br>
                <br>
                <strong>POST /players </strong><br>
                    Create a new player <br>
                <strong>GET /players/{id}</strong> <br>
                    Get details by player's id<br>
                <strong>DELETE /players/{id}</strong> <br>
                    Delete specific player<br>
                <strong>GET /players/{id}/characters</strong><br>
                    Get a list of owned characters<br>
                <strong>PATCH /players/{id}/characters/{code}/sell</strong><br>
                    Sell owned character<br>
                <strong>PATCH /players/{id}/characters/{code}/upgrade</strong><br>
                    Upgrade the owned character<br>
                <br>
                <br>
                <strong>Character options</strong><br>
                <br>
                <strong>GET /characters/{code}</strong><br>
                    Get the details of a specific character<br>
                <strong>DELETE  /characters/{code}</strong><br>
                    Delete character by code<br>
                <strong>GET /characters/{code}/appearance</strong><br>
                    Get the ascii art of a specific character<br>
                <strong>GET /characters</strong><br>
                    Get the list of all possible characters<br>
                <br>
                <br>
                <strong>Roulette options</strong><br>  
                <strong>POST /roulette/pulls</strong><br>
                    Make a pull<br>
                <br>
                <br>
                <strong>Fight options</strong><br>
                <br>
                <strong>GET /fights</strong><br>
                    Get the list of available fights<br>
                <strong>POST  /players/{id}/characters/{code}/fights/{fightId}</strong><br>
                    Choose the fight you want to take part in and fight an enemy<br>
                <br>
                <br>
                There are also some things you should take into account before playing GachaGirls: <br>
                <br>
                <strong>1.</strong> Every player has a default character in their collection. Although you can't sell your default character,<br> 
                the rest of options are available for it.<br>
                <strong>2.</strong> Your starting balance is 100 gold. Each pull costs 50 gold.<br>
                <strong>3.</strong> If you want to sell the character, the selling price is 35% less than the starting price.<br>
                <strong>4.</strong> For the fight you can choose characters with different difficulties. You can enter fights for free, <br>
                but if yot character has lost then 600 gold is withdrawn from your account. Otherwise, if your character wins you get 1000 gold as a reward.<br>
                <strong>5.</strong> When making a pull you have a chance of winning the characters with different probabilities: Rare, Epic and Legendary.<br>
                If you obtained a character that is already present in your collection then you get a compensation of 200 gold, otherwise a new character is added to your collection.<br>
                <br>
                <br>
                Our game is still in development and new features will be added with time. We hope you will have fun and enjoy our game! ^^<br>
                <br>
                <pre>
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠉⠁⠀⣠⡴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠓⠦⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⡴⠎⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠳⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠋⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣆⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⠀⢀⡾⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣦⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠃⠀⠀⣠⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄⠘⣧⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⣰⠋⠀⠀⠀⠀⠀⢠⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⠰⡾⣆⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⠀⣸⠃⠀⠀⣰⠃⠀⠀⠀⠀⠀⢠⡟⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⡆⢱⡹⡄⠀⠀⠀⠀
                ⡄⠀⠀⠀⠀⠀⠀⢀⡏⠀⠀⢰⠏⠀⠀⠀⠀⠀⢀⡟⠀⡇⠀⠀⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢣⠀⢷⢳⠀⠀⠀⠀
                ⠙⣆⠀⠀⠀⠀⠀⢸⠁⠀⢠⡏⠀⠀⠀⠀⠀⠀⣼⠀⠀⣧⠀⠀⠀⢨⢧⠀⠀⠀⠀⠀⠀⠀⠀⢠⠆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡜⠘⣮⡟⣇⠀⠀⠀
                ⠀⠘⣦⠀⠀⠀⠀⣿⠀⠀⡼⠁⠀⠀⠀⠀⢀⣼⠇⠀⠀⢿⠀⠀⠀⢸⠸⣷⡀⠀⠀⠀⠀⠀⠀⣾⣴⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠹⣽⣿⠀⠀⠀
                ⠀⠀⠘⣆⠀⠀⠀⡟⠀⢠⠇⢠⣰⠂⠀⠀⡾⡏⠀⠀⠀⠸⡆⠀⠀⠘⡇⢹⣇⠀⠀⠀⠀⠀⠀⣿⣿⡇⠀⠀⠀⠀⢀⢠⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡞⠀⠀⢹⠀⠀⢳⣿⡆⠀⠀
                ⠀⠀⠀⠹⡀⠀⠀⡇⠀⢸⢀⡞⣿⠀⠀⢸⣿⣁⣀⡀⠀⠀⢷⣀⠀⠀⢷⠀⣿⣆⠀⠀⠀⠀⠀⣿⣿⣧⠀⠀⠀⠀⠸⡸⢹⠀⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⢸⡶⠀⢸⣷⡇⠀⠀
                ⠀⠀⠀⠀⠇⠀⢸⡇⠀⣾⣮⡇⣿⠀⠀⡟⡿⠀⠀⠉⠛⠳⢾⣾⡀⢠⠸⡇⠘⣿⢦⠀⠀⢆⠀⣿⡇⣿⡆⠀⠀⠀⠀⡇⣽⡇⠀⠀⠀⠀⠀⠀⠀⢸⡇⢠⡆⢸⡇⠀⡄⣿⡇⠀⠀
                ⠀⠀⠀⠀⢠⠀⢸⡇⠀⢸⢹⢻⢻⠀⢰⣿⡇⠀⠀⠀⠀⠀⠀⠹⣧⣸⣧⣻⡀⠸⣿⣿⣆⡼⡆⣿⣇⣿⣿⣦⠀⠀⠀⣿⡏⣇⠀⠀⠀⢠⠀⠀⠀⢸⠃⣸⣇⠈⡇⠀⡇⠻⡇⠀⠀
                ⠀⠀⠀⠀⢸⠀⢸⡇⠀⢸⡼⡟⡞⣇⠀⣿⣧⣤⣴⣶⡒⠶⠦⣤⠹⣿⣻⣿⣧⡀⢻⣿⣿⣿⣿⣿⡟⢻⡾⣽⣷⣦⠀⠘⣧⣿⡀⠀⠀⠈⠀⠀⠀⣿⢀⣿⣸⠀⣇⠀⢹⢰⠇⠀⠀
                ⠀⠀⠀⠀⢸⠀⢸⡇⠀⠘⡇⠹⣿⣿⣔⣿⠋⠉⢹⣿⡿⣿⣶⣙⣧⠈⠳⣽⣿⣷⡄⢿⣿⠻⣷⣿⣷⠀⢻⣻⣽⣿⣧⠀⣹⣿⣿⡀⠀⡆⠀⠀⠀⣿⣜⢿⣿⠀⣿⠀⢸⣿⠀⠀⠀
                ⠀⠀⠀⠀⡾⠀⢸⠇⠀⠀⢧⡀⢸⠋⠻⣷⠀⠀⢸⣧⣸⣿⣿⠻⣟⠃⠀⠀⠀⠉⠋⠘⠟⠀⠉⣹⣷⣤⣴⠿⢿⣻⣿⣧⡇⡾⢿⣇⠀⣷⠀⠀⢰⣿⠉⠙⠻⣄⣿⠀⣼⠇⠀⠀⠀
                ⠀⠀⠀⢀⡇⠀⢸⠀⡄⠀⠈⠃⣾⠀⠀⠘⣺⢧⣈⠛⠷⠿⠛⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣧⠾⠿⣻⣿⢿⣿⣿⢿⣷⡇⠸⣿⠀⣿⠀⠀⠀⡟⠓⢦⣀⠙⣿⣠⠏⠀⠀⠀⠀
                ⠀⠀⠀⣾⠁⠀⢸⠀⡇⠀⠀⠀⡿⠀⠀⠀⠈⠙⠚⠝⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣹⣬⣿⡟⢿⣾⣃⠀⢹⡇⣿⠀⠀⢠⣧⠖⠒⢻⠀⠈⣯⠀⠀⠀⠀⠀
                ⠀⠀⣰⠃⠀⠀⢸⠀⠅⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠘⠿⣴⣿⠿⠃⠀⠙⠿⠟⢹⣁⣿⣠⡇⢹⠁⠀⢀⡾⠀⢠⡟⠀⠀⠀⠀⠀
                ⠀⢰⠏⠀⠀⠀⢸⠀⠁⠀⠀⢸⡅⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠛⠲⠶⠶⠶⠀⠀⠀⠀⢸⢹⣽⢸⠀⢸⠀⠀⣸⠃⣠⡟⠀⠀⠀⠀⠀⠀
                ⣴⠏⠀⠀⠀⠀⢸⠀⠀⠀⠀⢸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⡇⠘⠂⣾⠀⢀⣇⣴⡏⠀⠀⠀⠀⠀⠀⠀
                ⢯⣀⡀⠀⠀⠀⢸⡀⢸⠀⠀⡜⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⢿⣿⡇⠀⠀⣿⠀⢸⣹⡟⠀⠀⠀⠀⠀⠀⠀⠀
                ⣀⠈⠙⠓⣦⣤⢸⡇⢸⠀⠀⡇⢸⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠛⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠞⠉⠀⣸⡟⠀⠀⠀⡇⣰⠇⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠉⠉⠉⠉⠉⠁⢸⡇⢸⡄⠀⡇⠀⢻⣆⠀⠀⠀⠐⢦⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠖⠋⠀⠀⠀⠀⣿⠀⠀⠀⢰⡇⠁⣰⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠸⡇⢸⡇⢸⡇⠀⣸⠿⣆⠀⠀⠀⠀⠈⠳⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⠁⠀⠀⠀⠀⠀⠀⡇⠀⠀⠀⢸⣠⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⠀⣇⢸⡇⢸⡏⠉⢹⡆⠹⣆⠀⠀⠀⠀⠀⠀⠈⠉⠙⠛⠛⠿⠆⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⠀⠀⠀⠀⠀⠀⢀⡇⠀⠀⠀⢺⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⢠⣿⢸⡇⣸⡇⠀⢸⠃⠀⠹⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠏⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⡞⠲⣤⡤⠤⠤⠴⠶⠶⠶⠤⠤⢤⣀
                ⠀⠀⠀⠀⠀⠀⣾⢻⠀⡇⢻⡇⠀⢸⠀⠀⠀⠙⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⡇⠀⠀⠉⠒⢤⡀⠀⠤⠤⣤⣄⣀⣈
                ⠀⠀⠀⣠⣤⠴⣿⠈⡆⢿⣾⣧⣄⣸⠀⠀⠀⠀⠈⢦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⢞⡿⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⠀⠀⢠⡇⠀⠀⠀⠀⠀⠙⠲⠀⠀⠀⠀⠀⠀
                ⡤⠖⠋⠁⠀⠀⢿⠀⢷⣾⢹⡇⠙⢿⣤⡀⠀⠀⠀⠀⠙⠿⠶⣦⣤⣤⣠⡤⠴⠶⠞⠛⣩⠵⠖⠁⢻⡀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⠀⠀⣿⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀
                ⠀⠀⠀⠀⠀⠀⣾⡄⠸⣿⢸⡇⠀⠀⠈⠻⢶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠞⠋⠁⠀⠀⠀⠀⠻⣦⡀⠀⠀⠀⠀⣠⣾⣿⠀⠀⠀⣿⠀⠙⢦⣀⡴⠛⠋⠉⠉⠛⠦⣄⠀⠀
                ⠈⠀⠀⠀⠀⠘⠸⡇⠀⢹⣸⡇⠀⠀⠀⠀⠀⢉⡻⢦⣄⣀⠀⠀⣀⡤⠴⠛⠁⡀⠀⠀⠀⠀⠀⠀⠀⠀⢙⣿⣦⣀⡀⠀⠉⠋⣟⠀⠀⢸⣹⢀⣤⠖⠃⠀⠀⠀⠀⠀⠀⠀⠈⠳⣄
                </pre>
                <br>
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
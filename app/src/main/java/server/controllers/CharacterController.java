package server.controllers;

import commons.Character;
import commons.OwnedCharacter;
import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.service.CharacterService;
import server.service.PlayerService;
import server.service.RouletteService;

import javax.xml.stream.events.Characters;
import java.util.List;

@RestController
public class CharacterController {

    private final CharacterService characterService;

    private final RouletteService rouletteService;

    private final PlayerService playerService;


    @Autowired
    public CharacterController(CharacterService characterService, RouletteService rouletteService, PlayerService playerService) {
        this.characterService = characterService;
        this.rouletteService = rouletteService;
        this.playerService = playerService;
    }


    @PostMapping("/characters/seed")
    public void seedDatabase(){
        this.characterService.seedDatabase();
    }

    @GetMapping(path = "/characters/{code}")
    public ResponseEntity<Character> getById(@PathVariable long code) {
        if (code < 0 || !characterService.exists(code)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(characterService.getCharacterById(code));
    }

    @DeleteMapping(path = "/characters/{code}")
    public ResponseEntity<Character> deleteById(@PathVariable long code) {
        if (code < 0 || !characterService.exists(code)) {
            return ResponseEntity.badRequest().build();
        }
        Character ch = characterService.getCharacterById(code);
        characterService.deleteCharacter(code);
        return ResponseEntity.ok(ch);
    }

    @GetMapping("/characters/{code}/appearance")
    public ResponseEntity<String> getCharacterAppearance(@PathVariable long code) {
        String appearance = characterService.getCharacterAppearance(code);
        return ResponseEntity.ok(appearance);
    }

    @GetMapping("/characters")
    public ResponseEntity<List<Character>> getPossibleCharacters() {
        List<Character> possibleCharacters = characterService.getPossibleCharacters();
        return ResponseEntity.ok(possibleCharacters);
    }


//    @PostMapping("/characters/{code}/add")
//    public ResponseEntity<List<OwnedCharacter>> addCharacterToOwned(@PathVariable long id, @PathVariable long code){
//        if (id < 0 || !playerService.exists(id)) {
//            return ResponseEntity.badRequest().build();
//        } else if (code<0 || !characterService.exists(code)){
//            return ResponseEntity.badRequest().build();
//        }
//
//        return ResponseEntity.ok(ownedCharacterService.addCharacterToPlayer(id, code));
//    }








}

package server.controllers;

import commons.Character;
import commons.OwnedCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.service.CharacterService;
import server.service.RouletteService;

import javax.xml.stream.events.Characters;
import java.util.List;

@RestController
public class CharacterController {

    private final CharacterService characterService;

    private final RouletteService rouletteService;


    @Autowired
    public CharacterController(CharacterService characterService, RouletteService rouletteService) {
        this.characterService = characterService;
        this.rouletteService = rouletteService;
    }

    @PostMapping("/characters/seed")
    public void seedDatabase(){
        this.characterService.seedDatabase();
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








}

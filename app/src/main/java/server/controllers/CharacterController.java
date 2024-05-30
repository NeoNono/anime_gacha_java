package server.controllers;

import org.springframework.web.bind.annotation.RestController;
import server.service.CharacterService;
import server.service.RouletteService;

@RestController
public class CharacterController {
    private final CharacterService characterService;

    private final RouletteService rouletteService;

    public CharacterController(CharacterService characterService, RouletteService rouletteService) {
        this.characterService = characterService;
        this.rouletteService = rouletteService;
    }




}

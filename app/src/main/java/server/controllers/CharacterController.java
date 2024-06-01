package server.controllers;

import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import server.service.CharacterService;
import server.service.RouletteService;

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





}

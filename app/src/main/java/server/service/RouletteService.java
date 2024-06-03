package server.service;

import commons.Character;
import commons.Fight;
import commons.OwnedCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.CharacterRepository;
import server.database.OwnedCharacterRepository;
import server.database.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

import static commons.Rarity.*;

@Service
public class RouletteService {

    private final OwnedCharacterRepository ownedCharacterRepository;

    private final PlayerRepository playerRepository;

    private final CharacterRepository characterRepository;

    @Autowired
    public RouletteService(OwnedCharacterRepository ownedCharacterRepository,
                           PlayerRepository playerRepository, CharacterRepository characterRepository) {
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.playerRepository = playerRepository;
        this.characterRepository = characterRepository;
    }

    public Optional<Character> pullRoulette(long playerId) {
        Random rng = new Random();
        int roll = rng.nextInt(100);
        Optional<Character> character = Optional.empty();
        if (roll < 5) {
            // character = getRandomLegendary();
            // if (character.isEmpty()) {
                    // player.addBalance(500);
            //      return character;
            // }
            // else {
            //     return character;
        } else if(roll<25){
            // character = getRandomRare();
        } else {
            // character = getRandomRegular();
        }
    }

    private Optional<Character>getRandomLegendary(){

    }






}

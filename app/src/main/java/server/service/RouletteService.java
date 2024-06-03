package server.service;

import commons.Character;
import commons.OwnedCharacter;
import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.CharacterRepository;
import server.database.OwnedCharacterRepository;
import server.database.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class RouletteService {

    private final OwnedCharacterRepository ownedCharacterRepository;

    private final PlayerRepository playerRepository;

    private final CharacterRepository characterRepository;

    private final CharacterService characterService;
    @Autowired
    public RouletteService(OwnedCharacterRepository ownedCharacterRepository, PlayerRepository playerRepository,
                           CharacterRepository characterRepository, CharacterService characterService) {
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.playerRepository = playerRepository;
        this.characterRepository = characterRepository;
        this.characterService = characterService;
    }

    public Optional<List<OwnedCharacter>> pullRoulette(long id) {
        Player player = playerRepository.findById(id).orElseThrow();
        List<OwnedCharacter> ownedCharacters = ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
        if (player.getBalance() < 50) {
            throw new IllegalStateException("This is your default character, it can't be sold!");
        }
        Random rng = new Random();
        int roll = rng.nextInt(100);

        Optional<Character> character;
        if (roll <= 5) {
            character = getRandomLegendary(ownedCharacters);
        }
        else if (roll <=30){
            character = getRandomRare(ownedCharacters);
        }
        else
            character = getRandomRegular(ownedCharacters);
        //duplicates
        if (character.isEmpty()) {
            player.setBalance(player.getBalance() + 300);
            playerRepository.save(player);
            return Optional.empty();
        } else {
            return Optional.of(characterService.addCharacterToPlayer(id, character.get().code)); //add character to collection
        }
    }

    private static Optional<Character> getRandomLegendary (List<OwnedCharacter> ownedCharacters) {
        //TODO: getting the codes of all legendary characters and randomly assigning
    }

    private static Optional<Character> getRandomRare (List<OwnedCharacter> ownedCharacters) {
        //TODO
    }

    private static Optional<Character> getRandomRegular (List<OwnedCharacter> ownedCharacters) {
        //TODO
    }



}
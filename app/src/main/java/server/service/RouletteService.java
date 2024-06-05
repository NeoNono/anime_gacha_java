package server.service;

import commons.Character;
import commons.OwnedCharacter;
import commons.Player;
import commons.Rarity;
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
            throw new IllegalStateException("You don't have enough gold for a pull");
        }
        Random rng = new Random();
        int roll = rng.nextInt(100);

        Optional<Character> character;
        if (roll <= 4) {
            character = this.getRandomCharacter(ownedCharacters, Rarity.LEGENDARY);
        }
        else if (roll <=29){
            character = this.getRandomCharacter(ownedCharacters, Rarity.RARE);
        }
        else
            character = this.getRandomCharacter(ownedCharacters, Rarity.REGULAR);
        //duplicates
        if (character.isEmpty()) {
            player.setBalance(player.getBalance() + 200);
            playerRepository.save(player);
            return Optional.empty();
        } else {
            return Optional.of(characterService.addCharacterToPlayer(id, character.get().code)); //add character to collection
        }
    }

    private Optional<Character> getRandomCharacter(List<OwnedCharacter> ownedCharacters, Rarity rarity) {
        Random rng = new Random();
        List<Character> legendaryCharacters = this.characterRepository.findAllByRarity(rarity);
        int roll = rng.nextInt(legendaryCharacters.size());
        Character rolledChar = legendaryCharacters.get(roll);
        if (ownedCharacters.stream()
                .map(ownedCharacter -> ownedCharacter.ownedCharacterId.character)
                .toList().contains(rolledChar)) {
            return Optional.empty();
        }
        return Optional.of(rolledChar);
    }
}
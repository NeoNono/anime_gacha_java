package server.service;

import commons.Character;
import commons.OwnedCharacter;
import commons.OwnedCharacterId;
import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import server.database.CharacterRepository;
import server.database.OwnedCharacterRepository;
import server.database.PlayerRepository;
import java.util.Optional;

import java.util.List;
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    private final OwnedCharacterRepository ownedCharacterRepository;

    private final CharacterRepository characterRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, OwnedCharacterRepository ownedCharacterRepository, CharacterRepository characterRepository) {
        this.playerRepository = playerRepository;
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.characterRepository = characterRepository;
    }

    public Character getDefaultCharacter() {
        return this.characterRepository.findByName("Vendy").orElseThrow(() -> new RuntimeException("Default character not found"));
    }

    public boolean exists(long id) {
        return playerRepository.existsById(id);
    }

    public Player getPlayerById(long id) {
        return playerRepository.findById(id).get();
    }
    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }


    public Player createPlayer() {
        Player player = playerRepository.save(new Player());

        ownedCharacterRepository.save(new OwnedCharacter(new OwnedCharacterId(getDefaultCharacter(), player)));
        return player;
    }

    public int getPlayerBalance(long id){
        Player player = playerRepository.findById(id).orElse(null);
        return player.getBalance();
    }


      public List<OwnedCharacter> getPlayerCharacters(long id){
        return ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
    }


    public List<OwnedCharacter> sellGivenCharacter(long id, long code){
        if(code == getDefaultCharacter().code) {throw new IllegalStateException("This is your default character, it can't be sold!");}
        Player player = playerRepository.findById(id).orElseThrow();
        Character character = characterRepository.findById(code).orElseThrow();
        OwnedCharacter ownedCharacter = ownedCharacterRepository.findById(new OwnedCharacterId(character, player)).orElseThrow();
        player.setBalance((int) (player.getBalance() + character.getSELL_COEFF()));
        ownedCharacterRepository.delete(ownedCharacter);
        playerRepository.save(player);

        return ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
    }

    public OwnedCharacter upgradeOwnedCharacter(long id, long code){
        Player player = playerRepository.findById(id).orElseThrow();
        Character character = characterRepository.findById(code).orElseThrow();
        OwnedCharacter ownedCharacter = ownedCharacterRepository.findById(new OwnedCharacterId(character, player)).orElseThrow();

        if(player.getBalance()<500){
            throw new IllegalStateException("You don't have enough money for an upgrade!");
        }
        player.setBalance(player.getBalance()-500);
        ownedCharacter.setDamage(ownedCharacter.getDamage()+1);
        ownedCharacter.setHealth(ownedCharacter.getHealth()+4);
        ownedCharacter.setStamina(ownedCharacter.getStamina()+2);
        ownedCharacter = ownedCharacterRepository.save(ownedCharacter);
        playerRepository.save(player);

        return ownedCharacter;
    }

}

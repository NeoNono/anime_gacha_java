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

    private Character defaultCharacter;
    @Autowired
    public PlayerService(PlayerRepository playerRepository, OwnedCharacterRepository ownedCharacterRepository, CharacterRepository characterRepository) {
        this.playerRepository = playerRepository;
        this.ownedCharacterRepository = ownedCharacterRepository;
        this.characterRepository = characterRepository;
     //   defaultCharacter = this.characterRepository.findById(Long.valueOf(11)).get();
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

           Character defaultCharacter = characterRepository.findById(13L).orElseThrow(() -> new RuntimeException("Default character not found"));

        System.out.println("found the default character with ID " + defaultCharacter.code);

        ownedCharacterRepository.save(new OwnedCharacter(new OwnedCharacterId(defaultCharacter, player)));
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
        if(code == 13L) {throw new RuntimeException("This character can't be sold!");}
        Player player = playerRepository.findById(id).orElse(null);
        OwnedCharacter ownedCharacter = (OwnedCharacter) ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
        Character character = characterRepository.findById(code).orElse(null);

        player.setBalance((int) (player.getBalance() + character.getSELL_COEFF()));
        ownedCharacterRepository.delete(ownedCharacter);
        playerRepository.save(player);

        return ownedCharacterRepository.findAllByOwnedCharacterIdPlayerId(id);
    }

}

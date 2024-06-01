package server.service;

import commons.Character;
import commons.OwnedCharacter;
import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.OwnedCharacterRepository;
import server.database.PlayerRepository;

import java.util.List;
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    private final OwnedCharacterRepository ownedCharacterRepository;
    @Autowired
    public PlayerService(PlayerRepository playerRepository, OwnedCharacterRepository ownedCharacterRepository) {
        this.playerRepository = playerRepository;
        this.ownedCharacterRepository = ownedCharacterRepository;
    }


    public Player addPlayer(Player player){
        return playerRepository.save(player);
    }

    public boolean exists(long id) {
        return playerRepository.existsById(id);
    }

    public Player getPlayerById(long id) {
        return playerRepository.findById(id).get();
    }
    public List<Player> getPlayers() {
        return playerRepository.findAll();
    }

    public void deletePlayer(long id) {
        playerRepository.deleteById(id);
    }


    public Player createPlayer() {   //assign character for a player after defining player's id
        Player player = playerRepository.save(new Player());
        return ;
    }

    public int getPlayerBalance(long id){
        Player player = playerRepository.findById(id).orElse(null);
        return player.getBalance();
    }


}

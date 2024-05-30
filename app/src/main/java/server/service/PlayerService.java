package server.service;

import commons.Character;
import commons.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.PlayerRepository;

import java.util.List;
@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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


    public Player createPlayer(long id) {
        Player player = new Player();
        player.setId(id);
        return playerRepository.save(player);
    }

    public int getPlayerBalance(long id){
        Player player = playerRepository.findById(id).orElse(null);
        return player.getBalance();
    }


}

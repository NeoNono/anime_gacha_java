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

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).get();
    }


}

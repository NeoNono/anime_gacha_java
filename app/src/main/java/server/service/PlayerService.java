package server.service;

import commons.Character;
import org.springframework.beans.factory.annotation.Autowired;
import server.database.PlayerRepository;

import java.util.List;

public class PlayerService {
    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Character> getCollection(){

    }
}

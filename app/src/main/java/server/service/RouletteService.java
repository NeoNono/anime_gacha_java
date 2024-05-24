package server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.database.OwnedCharacterRepository;

@Service
public class RouletteService {

    private final OwnedCharacterRepository ownedCharacterRepository;
    @Autowired
    public RouletteService(OwnedCharacterRepository ownedCharacterRepository) {
        this.ownedCharacterRepository = ownedCharacterRepository;
    }





}

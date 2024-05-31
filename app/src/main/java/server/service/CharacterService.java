package server.service;

import commons.Character;
import commons.Rarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import server.database.CharacterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

//    public static final List<Character> CHARACTERS = List.of(
//            new Character("hehe", Rarity.RARE, 1, 2, 3, 4)
//    );

    @Autowired
    public CharacterService(CharacterRepository characterRepository){this.characterRepository=characterRepository;}

    public boolean exists(long code) {
        return characterRepository.existsById(code);
    }
}

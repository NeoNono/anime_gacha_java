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

//
//    public List<Character>possibleCharacters = List.of(new Character("Raiden Shogun", RARE, 40, 20, 8, 3500 ),
//            new Character("Shrek", LEGENDARY, 50, 25, 9, 5000),
//            new Character("Ayanami Rei", REGULAR, 35, 15, 4, 2000),
//            new Character("Lucy Heartfilia", REGULAR, 30, 15, 3, 1500),
//            new Character("Boa Hancock", RARE, 40, 18, 5, 3700),
//            new Character("Asuka Langley Soryu", RARE, 38, 15, 5, 3700),
//            new Character("Nezuko", LEGENDARY, 45, 15,9, 4500 ));

    @Autowired
    public CharacterService(CharacterRepository characterRepository){this.characterRepository=characterRepository;}

    public boolean exists(long code) {
        return characterRepository.existsById(code);
    }
}

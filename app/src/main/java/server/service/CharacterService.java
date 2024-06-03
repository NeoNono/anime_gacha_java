package server.service;

import commons.Character;
import commons.Rarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import server.database.CharacterRepository;
import server.database.OwnedCharacterRepository;

import java.util.ArrayList;
import java.util.List;

import static commons.Rarity.*;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final OwnedCharacterRepository ownedCharacterRepository;


    public List<Character> possibleCharacters =
            List.of(new Character("Raiden Shogun", RARE, 38, 20, 8, 4000,raidenAscii()),
                    new Character("Shrek", LEGENDARY, 50, 25, 9, 5000, shrekAscii()),
                    new Character("Ayanami Rei", REGULAR, 30, 15, 5, 2000, ayanamiAscii()),
                    new Character("Mitsuri Kanrodji", REGULAR, 27, 15, 6, 2500, mitsuriAscii()),
                    new Character("Boa Hancock", LEGENDARY, 50, 25, 10, 5500, boaAscii()),
                    new Character("Asuka", RARE, 38, 20, 7, 3500, asukaAscii()),
                    new Character("Nezuko", RARE, 40, 20,6, 3500, nezukoAscii()),
                    new Character("Sakura Haruno", REGULAR, 30, 15,5, 2000, sakuraAscii()),
                    new Character("Yumeko Yabami", REGULAR, 30, 15,5, 2000, yumekoAscii() ),
                    new Character("Vendy", REGULAR, 25, 15,4, 1500, vendyAscii())
            );



    @Autowired
    public CharacterService(CharacterRepository characterRepository, OwnedCharacterRepository ownedCharacterRepository){
        this.characterRepository = characterRepository;
        this.ownedCharacterRepository = ownedCharacterRepository;
    }

    public List<Character> getPossibleCharacters() {
        return this.characterRepository.findAll();
    }

    public void seedDatabase(){
        for(Character character : possibleCharacters){
            this.characterRepository.save(character);
        }
    }

    public String getCharacterAppearance(long code){
        Character character = characterRepository.findById(code).orElse(null);
        return character.getAppearance();
    }


    public boolean exists(long code) {
        return characterRepository.existsById(code);
    }
    public static String raidenAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⣤⠞⠛⣻⣧⣷⣾⣿⣿⣿⣿⣶⣶⣯⣤⣀⡀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⢀⢠⠀⠀⠀⢔⣾⣷⠟⠁⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣭⡢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⢀⢴⣿⣯⡻⣟⣵⣿⣿⣁⠨⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⣽⡿⣹⣾⠷⡿⢿⣿⢿⣿⠀⡄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣦⡠⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢀⣼⠿⣽⣿⠁⢎⡀⢌⣠⠏⢻⡆⢸⣿⣿⢿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣻⢿⣿⣿⣿⣿⣷⡠⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢸⣯⢿⣿⡿⡀⣘⠎⠙⢶⡀⠌⣿⠑⣿⣯⢿⡿⣿⣿⣯⣟⡿⣿⣿⡿⣿⣽⣟⡿⣭⣟⣾⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢸⢯⣿⣿⣷⡑⠑⠲⡄⡖⠛⠒⣼⣿⣿⣽⣯⣿⣿⣿⡷⣯⣿⣽⣳⢿⣽⣻⣾⣽⣿⣿⣿⣾⣿⣿⣿⣿⣧⠂⠀⠀⠀⠀" + System.lineSeparator() +
                "⢸⣯⣿⣿⣿⠿⣧⣆⠻⣄⣶⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀" + System.lineSeparator() +
                "⠘⣷⣿⡙⠁⢀⣻⣿⣿⠛⠻⢿⣿⣿⣿⣿⡿⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀" + System.lineSeparator() +
                "⠀⢽⣥⣷⠂⡀⢠⣿⣯⣑⣠⣾⣿⣿⣿⣿⠃⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣣⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠙⣿⣿⢿⣶⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣻⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⢰⣿⡏⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣗⠀⣀⣀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣯⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⣿⠇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠉⠀⠀⠀⠈⠻⢿⣯⡻⢿⣿⣟⣿⣿⣿⠿⠿⠿⠟⢻⣿⣿⣿⣿⣿⢇⠀⠀" + System.lineSeparator() +
                "⠀⢰⡟⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡛⠿⣆⠀⠀⠀⠀⠀⠀⠉⠁⠀⠉⠉⠉⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣟⣿⣯⠆⠀" + System.lineSeparator() +
                "⠀⠺⣧⣄⣠⣿⣿⡿⠛⠻⣿⡿⣿⣿⡇⢀⣠⣤⣤⣤⣅⣒⠀⠀⠀⠀⠀⠀⠀⣮⣶⡾⢿⢷⠖⣿⣿⣿⣿⣿⡹⣿⣧⠀" + System.lineSeparator() +
                "⠀⠀⢸⣿⣿⣿⡿⠠⠤⣄⡈⠧⢿⣿⣯⠛⠛⠛⠉⠉⠉⠙⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⡜⣿⡆" + System.lineSeparator() +
                "⠀⢠⣿⣿⣿⣿⣿⣄⠀⠘⠮⠖⠈⢿⣿⣧⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⡿⣽⣿⣸⠇" + System.lineSeparator() +
                "⠀⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣤⣶⣶⣽⣻⢿⣷⠦⠀⠀⠀⠀⢄⣀⣀⡀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⡿⠀" + System.lineSeparator() +
                "⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣤⣤⣀⣀⡀⣠⠞⢉⣶⡤⢤⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋⠀⠀" + System.lineSeparator() +
                "⠀⣻⣿⣿⣿⣿⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣯⣵⣶⣟⣳⢶⡄⠈⢻⣿⣿⣿⡿⣿⢿⠅⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠙⢿⣿⣿⣿⣷⣾⠯⣿⣿⣿⣧⣀⠀⠀⠈⠙⠛⠿⢿⡿⣿⣏⣁⣀⣉⣙⣣⠀⣠⣿⣿⣿⣷⣿⣭⣶⣤⡆⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠈⠉⠉⠉⣱⣻⣿⣿⣿⠋⠚⠻⠭⠷⣲⢤⣀⠀⠊⠛⢤⡈⣻⠋⠈⡽⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠎⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⢀⢰⣿⣿⣿⡿⠁⠀⠀⠀⢀⣠⣶⠞⠘⠯⡲⣄⣀⣽⣅⣀⡞⠀⢹⣿⣿⣿⣿⣿⡿⣿⡿⡅⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⣠⣿⣾⣿⣿⡿⠁⠀⠀⠀⠀⠉⣼⣯⣽⣶⣴⣶⣿⣿⣿⣿⣻⣻⢷⣾⡟⢿⣿⣯⣷⡿⣿⣿⠋⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠛⠿⢿⣿⣛⡶⣤⣀⣀⣀⣀⣀⣿⣿⣷⣿⣲⣌⡻⣛⣿⣿⣿⣿⡿⠟⡻⣿⣷⣿⣿⣧⣸⡇⠀⠀⠀⠀⠀";
    }

    public static String nezukoAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣬⣿⣿⣿⣿⣿⣿⣽⣿⣿⣭⣭⣅⡀⠀⠀⠈⠀⢐⠙⠓⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⡿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⡌⠀⠀⠀⢀⡽⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣟⣭⣶⣿⣿⣿⣿⣿⣶⣯⣿⣿⣿⣿⣞⢿⣿⣿⣶⠁⠀⠀⣠⠞⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⡿⠟⠁⣠⡞⠁⠀⠀⠀⣀⣤⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣾⣿⡿⠛⠉⠛⠉⠁⠀⠀⠀⠀⠀⠙⠛⠻⠿⣿⣿⣿⣟⡟⢦⠤⠺⠿⢿⡤⠴⠚⠉⠁⣾⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣶⣧⣀⠀⠀⠀⠀⠀⠀⠀⢠⡇⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⢿⣶⣤⣤⣤⣤⠤⠟⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⡏⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⠁⠀⢀⣀⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡠⠀⣠⣀⡀⠀⠀⠀⠀⣿⣿⣿⢹⣿⣿⣽⣿⡇⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⣾⡿⣿⡇⠀⢀⣥⣤⣶⣶⣬⣁⠀⠀⠀⠀⠀⠀⢹⣿⠶⠶⢶⣤⣭⣑⠢⡀⠀⣿⣿⣿⣿⣿⣿⣿⢿⣿⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⣿⣿⣿⣇⣼⠟⢉⡠⠄⠲⢄⠉⠓⠀⠀⠀⠀⠐⠁⣠⠔⠂⠒⠢⣍⠻⣷⣄⣀⣿⣿⣟⣿⣿⣿⣿⡾⣿⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⣿⣿⣾⣯⠃⣸⠋⡤⠀⡆⠀⠱⡄⠀⠀⠀⠀⠀⣾⠡⣋⣀⡆⠀⠈⣧⠈⢻⡌⣿⣿⣿⡏⠻⣿⣿⣿⢿⡄⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⠀⢿⡀⠈⠿⡇⠀⢀⡇⠀⠀⠀⠀⠀⣇⠀⠉⠛⠃⠀⠀⡟⠀⠀⠀⣿⣿⣿⣏⠂⢹⣿⣿⣾⡇⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⡆⣼⠣⠀⠀⠀⢀⡼⠁⠀⠀⠀⠀⠀⠙⠤⢀⡀⠀⡀⢜⣁⠀⠀⠀⣿⣿⣿⡏⠁⢸⣿⣿⣿⣧⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⢨⣿⣿⠟⢻⣇⡿⠷⡂⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣈⣩⠴⣟⣁⣀⠀⢰⣿⣿⣿⡇⢀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⣾⡿⠁⢠⠏⡸⠀⢰⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡿⠉⡼⢩⠃⢠⠈⣇⣸⣿⣿⣿⣶⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⢰⣿⡇⠀⠈⠀⡇⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⢸⠀⡎⠀⣼⣿⡿⣿⣿⣿⣿⢹⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⣾⣿⣷⡀⠸⣶⢳⡀⢳⣤⣤⣤⣀⣀⠀⠀⠀⠀⠀⠂⠀⣯⠀⢸⣤⣧⠀⣿⢿⣾⣿⣿⣿⣿⣼⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⡿⢟⣿⣿⣿⠛⠛⢿⡿⠉⠹⠿⠿⠾⠛⣦⠼⠛⠿⣦⣬⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠱⣄⠀⠀⢀⠔⠋⠀⠀⢀⣴⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠹⣿⣿⣿⣆⠀⠀" + System.lineSeparator() +
                "⠀⠀⢀⣿⣿⣿⡿⢛⣵⡿⣿⣿⣿⠟⠁⢧⠀⠀⠀⠀⠈⣢⠞⠁⠀⠀⠀⡰⠋⢀⣰⣿⣧⣿⣿⣿⣿⣸⣿⣿⣯⡻⣿⡇⢻⣿⣿⣿⣆⠀" + System.lineSeparator() +
                "⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⠜⢁⣸⣇⠀⢀⡠⠊⠁⠀⠀⠀⣠⠊⣠⠔⠉⢀⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠈⣿⣿⣿⣿⣆" + System.lineSeparator() +
                "⣴⣿⣿⣿⢟⣽⣿⣿⣿⣿⣿⡿⣣⠔⣩⠛⡏⢷⠋⠀⠀⠀⠀⡠⣎⠉⠁⢱⠀⠀⢸⢻⡿⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⡄⢹⣿⣿⣿⣿" + System.lineSeparator() +
                "⣿⣿⣿⣫⣿⣿⣿⣿⣿⣿⣿⠟⠁⡴⠁⢸⠀⠈⠣⠀⣀⣴⠋⡙⢌⠳⡀⠀⢇⠀⡏⢸⣿⡹⣿⣿⣿⣧⣻⣿⣿⣿⣿⣿⣷⡀⣿⣿⣿⣿" + System.lineSeparator() +
                "⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⡤⠎⠀⠀⢸⠀⣀⡴⠋⡱⠃⢠⠁⠈⠢⡘⢆⠸⠀⠇⢸⣿⣷⣽⣿⣿⣿⣧⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿";
    }

    public static String vendyAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⢻⡀⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⣻⠋⠀⠀⣇⠸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⣶⣶⣾⣿⣿⣿⣿⣶⣿⣦⣥⡀⠀⠀⣸⡀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⡋⢹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣠⣤⠤⣀⡀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢿⣿⡄⠀⠈⠉⠑⠒⠚⠛⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠘⣿⣷⡀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠹⣿⣧⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⢿⣿⣿⣿⣿⣧⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠹⣿⡷⠦⣄⡀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡜⣿⣿⣿⣿⣿⡞⣿⠻⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⢀⣾⣿⣄⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢻⣿⣷⢻⣿⣿⣿⣿⣷⣿⣷⠚⠻⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣻⣿⣧⣿⣿⣿⣿⣿⣼⣿⡄⠀⣈⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⢠⣿⣿⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠙⢿⣿⣏⠻⣿⣿⣿⣿⣿⣾⢻⡀⠙⣿⣿⣿⣿⣿⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⢸⣿⣿⣻⣿⣧⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢀⣀⣐⣻⣿⣆⠘⠟⠿⣿⡿⣿⣿⣧⠀⠿⣿⣿⣿⣿⡇⠀⠀⠀" + System.lineSeparator() +
                "⠀⣼⣿⣿⣻⣿⣿⡀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⡉⣆⠉⠃⠙⠂⠀⠈⠃⠹⣿⡿⢀⠀⣿⣿⣿⣿⣇⠀⠀⠀" + System.lineSeparator() +
                "⠀⣿⣿⣿⣿⣿⣿⣇⠀⠘⢿⣿⣿⡿⠿⢿⣿⣿⣿⣿⡇⢻⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠚⠩⠒⣿⣿⣿⣿⣿⠀⠀⠀" + System.lineSeparator() +
                "⠀⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠙⠿⣇⠖⠾⢿⣿⣿⣿⣧⠀⠻⠿⠿⢁⠀⠀⠀⠀⠀⠀⠃⠀⠀⠀⢠⣿⣿⣿⣿⣿⠀⠀⠀" + System.lineSeparator() +
                "⠀⣿⣿⣿⡈⣿⣿⣿⣧⠀⠀⠀⠀⠈⢣⡐⠼⣿⣿⣿⣿⡄⢀⢒⡚⠉⠀⠀⠀⠀⠀⠀⢀⡀⠀⢠⣿⣿⣿⣿⣿⣿⡀⠀⠀" + System.lineSeparator() +
                "⠀⣿⣿⣿⡇⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠈⠒⠚⢿⣿⣿⣷⡄⠈⠀⠀⠀⠀⣀⠀⢀⡠⠋⠀⣴⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀" + System.lineSeparator() +
                "⢸⣿⣿⣿⡇⢻⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣦⢤⣀⣀⡀⠈⠉⠉⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀" + System.lineSeparator() +
                "⢸⣿⣿⣿⡇⢸⣿⣿⣿⣿⡇⠀⣀⣀⣀⡀⠀⠀⠀⢸⢻⣿⣿⣿⣦⠀⠀⠈⠉⣏⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀" + System.lineSeparator() +
                "⢸⣿⣿⣿⣿⢸⣿⣿⣿⣿⡿⠋⠁⠀⠀⠉⠉⠓⣶⠜⠳⣿⣿⣿⣿⣷⣦⣤⣾⣭⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀" + System.lineSeparator() +
                "⣾⡇⣿⣿⣿⠀⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢦⡈⣿⣿⣿⣿⣿⣿⣿⣟⠻⡉⠙⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⠀⠀" + System.lineSeparator() +
                "⣿⡇⣿⣿⣿⡇⢻⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠙⢻⣿⣿⣿⣿⣿⣿⣏⢷⣌⠻⡁⠀⠀⠹⣿⣿⣿⣿⣿⢹⠀⠀" + System.lineSeparator() +
                "⣿⣇⣿⣿⣿⡇⠸⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⠄⢸⣿⣿⣿⠻⣿⣿⣿⣯⢿⣧⠙⢆⣄⠀⢹⣿⣿⣿⣿⢸⡇⠀" + System.lineSeparator() +
                "⣿⣟⢻⣿⣿⣿⠀⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⢸⣿⣿⣿⡆⢻⣿⣿⣿⣟⠛⠷⡈⣿⣦⡀⢿⣿⣿⣿⣆⣇⠀" + System.lineSeparator() +
                "⣿⣿⢸⣿⣿⣿⡆⢸⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠈⡇⢸⣿⣿⣿⣧⠘⢻⣿⣿⣿⣄⣤⣴⣿⣿⣿⣾⣿⣿⣿⡇⢿⠀" + System.lineSeparator() +
                "⠿⠿⠼⠿⠿⠿⠷⠬⠿⠿⠿⠿⠿⠷⠆⠀⠤⠴⠶⠿⠿⠿⠿⠿⠿⠿⠿⠷⠤⠾⠿⠿⠷⠘⠿⠿⠿⠿⠿⠿⠿⠿⠷⠼⠄";
    }

    public static String boaAscii(){
        return "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⡿⣿⡿⣿⣾⣿⣿⡿⣻⣾⣿⡟⣿⣿⣿⣿⢹⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⡿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣧⣿⣿⣟⣷⣿⣿⣿⣷⣿⣿⣿⣷⣾⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣷⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣺⣛⣻⡷⢶⣶⣆⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣔⣚⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣶⣌⠁⠀⠀⠀⠀⠀⠀⡰⢯⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠈" + System.lineSeparator() +
                "⣿⣿⣿⣿⣇⡙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠈⣙⠧⠀⠀⠀⠀⠀⠀⢸⢁⡔⢻⣯⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⠁⣩⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⡟⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⢾⠀⠈⠉⠙⠛⢻⣿⣿⣿⣿⣿⣿⣿⣿⣰⠃⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⡀⢣⠸⣿⣿⣿⣿⣿⣿⣿⣿⡿⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡆⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣷⣌⠳⣤⣻⣿⣿⣿⣷⡏⣸⢣⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣷⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣼⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣷⡦⣿⣽⣿⢿⣿⠁⣿⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡤⠖⠉⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⢻⠏⢸⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣿⡟⠛⣿⣹⣷⣄⠘⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣧⡟⠀⢸⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⣿⠀⢀⣿⣥⠈⢹⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠠⣶⠾⠯⠤⠤⢼⠃⠀⠀⠀⠀⣠⣾⣻⣿⢸⣿⡿⠈⠀⠀⢸⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⡇⠀⣸⢹⣷⣶⡞⢿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠈⠉⠒⠒⠈⠁⠀⠀⠀⢀⣼⠃⣾⣹⣿⡿⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⠁⢀⣿⢰⡏⠉⠀⠀⠙⢿⣿⣿⣿⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡇⢰⣿⡏⣿⡇⠀⠀⠀⠀⣰⣆⣼⡇⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣿⠀⢸⣿⣸⢷⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣶⣤⣄⣀⣀⣠⣤⣾⣿⣿⡿⠀⢸⣿⡇⢼⣇⠔⡹⣳⡶⢩⡿⢿⡅⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⡟⠀⣾⣿⣿⣻⠀⠀⠀⠀⠀⠀⠀⠻⣿⣸⡇⣿⣿⣷⣿⣿⣻⣿⣿⣿⣿⣿⡇⠀⣿⣿⣿⣶⡿⠞⠾⠏⠀⡸⠓⣯⠿⡄⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⣇⠀⣿⣿⡿⣼⡄⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣸⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⣿⣿⡿⢬⡇⠀⠀⣤⣶⠁⢰⠃⠀⠹⣄" + System.lineSeparator() +
                "⡻⣿⣿⣿⣿⣿⠀⢿⣿⢘⣻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠸⢻⣏⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠘⣿⣿⡿⢼⡇⠀⢀⢻⣿⠆⠁⠀⠀⠀⠈" + System.lineSeparator() +
                "⠉⠻⣯⠻⡟⣿⡀⠘⠛⠨⢽⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣹⢿⣿⣿⣿⣿⣿⣿⣿⡇⠀⣿⡏⣙⣺⡇⠀⡞⠘⠁⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠘⣾⣷⣲⡰⡉⠛⠳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⡇⠀⠉⢰⢤⣿⣃⠜⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠙⠋⠁⠱⡄⠦⠘⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣷⣄⠀⢛⣾⡿⠋⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀";
    }


    public static String ayanamiAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⣤⠤⠤⣤⣷⣎⣠⠴⠒⠊⠉⢑⠢⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⠈⠉⠀⠀⠀⠀⠀⠈⠹⣦⡶⠀⢀⣀⣠⠉⠲⢍⡙⠶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢶⣿⣋⣠⣄⣀⣀⠀⣀⠀⠙⠢⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣆⣀⠀⠀⠀⠀⠈⠙⠢⣄⡀⠑⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣏⠉⠑⠲⢤⣄⡀⠀⠀⠙⢦⡈⢳⣄⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠟⠁⠀⣠⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠚⠉⠀⠀⠀⠀⠀⣈⣳⡄⠑⢦⡈⠙⠶⣄⠀⠀⠉⠳⣝⢧⡀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⣠⡾⡿⠂⠀⠀⠀⠀⠀⠀⣠⣶⠟⠁⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠙⠆⠀⡌⠢⡀⠀⠙⠶⡀⠠⡈⢻⣿⡄⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⣠⡾⠋⡴⠁⠀⠀⠀⡀⢀⣴⣿⡟⠁⠀⠀⠀⠀⠀⠀⢴⠟⠀⠀⢠⡄⠀⡀⠀⠸⡀⠀⠀⠈⠢⡀⠀⡘⢦⠙⢿⡄⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⡋⢀⡼⠁⠀⢰⠀⠔⠁⣿⣿⠯⣤⡀⠀⢸⠇⠀⠀⢀⡎⠀⠀⠀⣸⠁⠀⣷⡀⠀⣿⠢⣰⡀⠠⣝⣦⡓⠀⠀⠚⣷⡀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⢠⣿⠟⣵⣿⠁⠀⠀⣾⠀⢀⡼⠋⠀⢀⣸⠃⢠⡏⠀⠀⣰⠟⠁⠀⠀⢠⡿⠀⠀⡇⢷⠀⣿⠀⠙⢷⡜⣇⠈⠛⢶⣄⠀⠘⣷⡀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⣸⠏⣼⢷⠃⠀⠀⢸⡟⢠⣾⡀⡀⠘⡽⠃⣠⡞⠀⠀⣰⠋⠀⠀⠀⢃⣾⠁⠀⠀⠃⠀⠇⢹⡇⠰⣠⡹⣞⢧⡲⢤⣽⡄⠀⠘⣧⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⢰⣿⣿⠃⡏⠀⠀⠀⢸⠇⡸⢿⠟⠁⣾⢁⡴⠃⣄⠀⢰⠃⠀⠀⠀⠀⢨⡿⠀⠀⠀⠀⠀⠀⠀⣇⠀⢹⡇⠙⢧⣿⠀⠻⡇⢠⠀⢸⡀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⣿⡏⢸⠃⠀⣰⠄⢸⠀⣇⠈⣀⣠⣿⠋⠀⢸⣇⠀⣿⠀⠀⠀⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⣰⣿⠀⢰⡇⠀⠈⢷⣄⣰⠇⠸⡇⢸⡇" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⢸⠁⡾⠀⣼⢿⡀⣿⠀⠈⠋⠉⣅⡿⠀⣰⡏⢸⡀⣿⡄⢰⠀⠀⣸⠃⠀⠀⠀⡄⠀⣼⢢⣿⣿⡇⢸⡇⠀⠀⣤⡽⢋⠀⢠⣇⣸⡇" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⣼⡏⠸⡇⣇⠀⠀⠀⣸⣿⣧⣰⡏⠀⠈⣧⡇⢿⣾⠀⢠⡟⠀⠀⢀⣼⠃⣸⣃⣼⢫⣿⡇⣾⠃⠀⢰⡿⠃⣼⠀⣸⢸⣿⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠘⣇⣼⢻⡇⠀⢻⢿⠀⠀⢀⣿⣿⣿⣿⣏⣙⡓⢿⣷⢼⣧⣠⡞⠀⢠⣴⣾⢃⣰⣿⣿⣧⣼⣿⣿⡿⠐⣠⡿⣁⣼⣿⢠⡏⢸⣿⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣾⣿⡀⠘⣿⡆⠀⣸⡟⢿⢿⣿⣿⣿⡛⠿⣿⡀⣯⣿⢃⣴⣿⣿⢃⣰⣿⣿⣿⠶⠿⣿⣿⣇⣼⣿⣷⣿⣿⣿⡿⠁⢸⡏⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣧⡹⣿⣇⢰⣿⣷⡉⠀⠛⠿⠿⠃⠀⠈⠁⣿⣷⡟⣿⢿⣿⣿⡿⣿⣿⣿⠇⠀⣹⣿⣿⣿⣿⣿⠋⣹⡿⠃⠀⠘⠁⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣷⡜⢿⣸⣿⢿⣷⣄⠀⠀⠀⠀⠀⢀⣾⣿⠋⠼⠉⣼⠿⠋⠀⠈⠉⠉⠀⣨⣿⣿⣿⣿⣿⣿⢀⡿⠁⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣾⣿⣿⣦⡹⣿⠷⣤⡀⠀⠀⠺⠟⠀⠀⢀⠞⠁⠀⠀⠀⠀⢀⣢⣿⣿⣿⣿⣿⣿⣿⣿⠈⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣽⣿⣿⣷⣉⡻⢾⣷⣄⠈⠉⠀⠀⠀⠀⠀⠀⠰⡂⠀⠀⠒⠊⠉⣉⣵⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠿⠛⠉⠀⠀⠉⠙⠻⢿⣶⢄⣀⠀⠀⠀⠀⢠⠞⠁⢀⣀⣤⣶⣿⣽⣿⣿⣿⣿⣿⡿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣯⡛⠒⠒⠒⠒⠒⠛⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⣿⡿⢡⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠒⠀⠀⠀⠀⢀⣠⣶⣿⣿⣿⣿⣿⡿⠋⣸⡟⠁⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣽⣷⣄⣀⣠⠴⠛⠋⢹⠟⣱⣿⠟⠋⠀⠀⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⢀⡠⠶⣻⡏⠀⠀⠀⢀⣤⠤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣧⠀⠀⠀⠀⠀⠰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⢀⡖⠉⠀⣰⣿⠃⠀⡇⣰⣟⢻⣿⣟⣿⣧⠀⠀⠀⠀⠀⠀⠀⣾⡏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⢠⠞⠀⠀⣸⣻⡏⠀⠀⣧⢻⡞⢻⠽⣯⣉⡟⠀⠀⠀⠀⠀⠀⣰⣯⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⣰⣿⣿⡇⠀⠀⣿⣦⠛⠶⠤⠼⠋⢁⠀⠀⠀⠀⠀⡰⣯⣿⡷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣦⣤⣤⢾⣿⣿⣿⡇⠀⠀⠘⣿⡛⠛⠂⠀⠀⠀⠀⠀⠀⠀⠋⢀⣼⣿⣷⣿⡝⠲⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣀⡀⠀⣼⣿⣿⣿⡇⠀⠀⠀⠀⠙⠀⠀⠀⠀⠀⠀⠀⠀⣼⢇⣿⠏⢿⣿⣿⣧⡀⠀⠉⠓⠦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣸⡇⢀⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢿⣿⡏⠀⠈⢻⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
    }

    public static String asukaAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⡀⣤⡴⠞⠙⠻⢍⡉⠉⢻⣶⣤⣀⣀⣀⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣾⣿⠟⠉⠀⣈⡃⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⣻⣿⣿⣿⣿⡤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠖⠋⣩⠟⣋⠿⠚⠁⠀⠀⡞⠀⡀⠀⠀⠉⡀⠀⠀⠀⡈⠳⣄⠀⠈⡉⠛⠿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⢻⠟⠁⢠⠆⡰⠀⢸⡏⠀⢧⠀⠀⡆⠉⢦⡀⠀⠀⠀⠀⠙⢦⡉⠲⣄⠈⠑⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⢠⢃⡔⡰⢁⠈⢀⠀⣸⣇⠀⢸⡄⠀⠘⣆⠀⢳⡀⠀⠈⢦⠀⠀⢿⣄⠈⢻⡓⢮⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠰⡄⠀⠀⠀⠀⠀⢀⣿⠛⣿⠁⣼⠄⢸⢀⣿⣿⣆⢸⡿⣧⠀⠸⣆⠈⡟⢦⣀⠈⣧⠀⠀⢻⣶⣄⢳⣌⢿⣗⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣆⠀⠀⠙⡄⠀⠀⠀⠀⠸⠇⠘⡇⣸⣿⠀⣾⣼⠦⠿⣿⣶⣇⠙⣷⡀⢿⣆⣇⣠⣽⣷⣿⠷⣄⠈⢿⣟⠳⣿⠘⡎⠆⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠈⢣⡀⠀⠘⡄⢃⠀⠀⠀⠀⣰⣿⡟⣇⠀⣿⡿⣠⡴⢻⣿⣿⠋⠉⠳⣼⡟⠋⣿⣿⡟⣿⠆⢹⣧⣸⡟⠀⠸⣇⡇⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣇⠀⠙⣄⠀⢱⢸⡄⠀⠀⢀⡟⢉⡇⠹⣴⣿⣧⠀⠁⠀⠁⠀⠀⣼⠀⠈⠁⠀⠀⠀⠀⠀⠀⢸⣿⣿⡇⠀⡄⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣧⡀⠈⣳⡄⠋⠀⠀⠀⠘⠀⢸⠀⡇⢻⣿⠾⡅⠀⠀⠀⠀⠀⣈⣀⡀⠀⠀⠀⠀⠀⠀⠀⣿⠉⣽⡇⠀⢇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⡄⠙⢿⣾⡟⢀⣤⡄⠀⠀⠀⠀⡇⠈⡁⡆⠘⣦⣵⡀⠀⠀⠀⠀⣿⣿⣿⡄⠀⠀⠀⠀⣴⣾⣥⢾⡿⣷⠀⠸⡀⢿⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠻⣆⠈⣿⢣⠸⡀⢱⠀⠀⠀⢰⠁⠀⡇⣹⠀⣿⠁⢿⣦⡀⠀⠀⠹⣿⣿⡇⠀⠀⠀⣴⠋⣸⡇⢸⠀⣿⠀⠀⢷⠘⡇⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠉⡿⠉⠻⢧⡇⢸⡇⠀⠀⡞⠀⠀⡇⣿⡇⣿⠀⣼⡇⡟⠢⡀⠀⠙⠋⠀⠀⣠⡞⠁⢸⣹⠃⢸⡀⢸⡆⠀⠘⣇⠹⡀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⣰⢁⣸⠖⢤⢹⡄⢧⠀⢠⠇⠀⠀⠃⡿⠃⢸⠀⣿⠀⣿⠀⢹⡷⣤⣤⣴⣛⣁⡇⢀⠇⣾⠀⢸⡇⠀⢷⠀⠀⠘⡄⢃⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢀⡞⠋⠀⣰⡀⢻⠀⠈⣇⣼⡏⠀⠀⢸⡇⠀⠸⣄⡿⠀⢹⣇⢈⡟⠛⠛⠛⠛⠛⠳⣼⣰⠿⢦⣼⣧⡤⠼⣦⣀⠀⠀⠸⡆⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠸⡆⠀⠹⠛⢷⣸⠀⠀⣿⣿⠁⠀⠀⢸⣇⣀⣠⡿⠗⠚⣺⡿⠟⢷⠀⠀⠀⠀⠀⢶⡉⣠⣶⠖⠉⠀⠀⠀⠀⠈⠑⢦⡀⢹⡀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠘⢆⠀⠀⠀⠙⡀⡼⣹⢻⠀⡠⠚⠁⠀⠀⢯⣤⣞⡟⠳⠤⣄⡜⠀⢀⣠⠤⠔⠊⢹⢃⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⣧⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⢈⡗⠀⠀⠀⡼⢁⡏⣆⡜⠁⠀⠀⠀⠀⠀⢸⢹⠇⠀⠀⠈⠒⠐⠋⠁⠀⠀⠀⡞⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣆⠀⠀⠀" + System.lineSeparator() +
                "⠀⢀⡾⠁⠀⠀⡸⠁⣸⠀⡿⠀⠀⠀⠀⠀⠀⠀⡄⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢣⠏⠀⠀⠀⠀⠀⣀⣠⠄⠀⠀⠀⠀⠀⠈⠑⢄⡀" + System.lineSeparator() +
                "⢀⡜⠀⠀⠀⢰⠃⠀⣇⡜⠁⠀⠀⠀⠐⣖⠀⢸⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠞⢸⡀⠀⠀⠀⣠⠞⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠉" + System.lineSeparator() +
                "⠎⠀⠀⠀⢠⠇⠀⡰⠋⠀⠀⠀⠀⠀⢀⣼⣷⡏⠀⠙⠢⡀⠀⠀⠀⠀⡠⠒⠁⠀⠀⠀⠙⢦⡀⠀⡟⠀⢀⡇⠈⢳⣤⡀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⣀⡏⡠⠊⠀⠀⠀⠀⠀⢀⣠⢞⣿⠋⠀⠀⠀⠀⠘⢦⣀⡰⠊⠀⠀⠀⠀⠀⠀⠀⠀⠙⣄⠃⢀⣾⠁⠀⠘⡇⠉⡲⢤⣄⠀⠀⠀";
    }

    public static String shrekAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⢀⣴⠟⠉⠀⠀⠀⠈⠻⣦⡀⠀⠀⠀⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣀⢀⣾⠿⠻⢶⣄⠀⠀⣠⣶⡿⠶⣄⣠⣾⣿⠗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⢻⣿⣿⡿⣿⠿⣿⡿⢼⣿⣿⡿⣿⣎⡟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⠉⠛⢛⣛⡉⠀⠀⠙⠛⠻⠛⠑⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣧⣤⣴⠿⠿⣷⣤⡤⠴⠖⠳⣄⣀⣹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣀⣟⠻⢦⣀⡀⠀⠀⠀⠀⣀⡈⠻⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡿⠉⡇⠀⠀⠛⠛⠛⠋⠉⠉⠀⠀⠀⠹⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡟⠀⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠀⠈⠑⠪⠷⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⣿⣿⣦⣼⠛⢦⣤⣄⡀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠑⠢⡀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠲⠖⠛⠻⣿⡿⠛⠉⠉⠻⠷⣦⣽⠿⠿⠒⠚⠋⠉⠁⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢦⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⢀⣾⠛⠁⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢣⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⣰⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣑⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⣰⣿⣁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣧⣄⠀⠀⠀⠀⠀⠀⢳⡀⠀" + System.lineSeparator() +
                "⠀⠀⠀⣿⡾⢿⣀⢀⣀⣦⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⣫⣿⡿⠟⠻⠶⠀⠀⠀⠀⠀⢳⠀" + System.lineSeparator() +
                "⠀⠀⢀⣿⣧⡾⣿⣿⣿⣿⣿⡷⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⢀⡴⢿⣿⣧⠀⡀⠀⢀⣀⣀⢒⣤⣶⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇" + System.lineSeparator() +
                "⠀⠀⡾⠁⠙⣿⡈⠉⠙⣿⣿⣷⣬⡛⢿⣶⣶⣴⣶⣶⣶⣤⣤⠤⠾⣿⣿⣿⡿⠿⣿⠿⢿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇" + System.lineSeparator() +
                "⠀⣸⠃⠀⠀⢸⠃⠀⠀⢸⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⠟⡉⠀⠀⠀⠈⠙⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇" + System.lineSeparator() +
                "⠀⣿⠀⠀⢀⡏⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠛⠉⠁⠀⠀⠀⠀⠀⠉⠠⠿⠟⠻⠟⠋⠉⢿⣿⣦⡀⢰⡀⠀⠀⠀⠀⠀⠀⠁" + System.lineSeparator() +
                "⢀⣿⡆⢀⡾⠀⠀⠀⠀⣾⠏⢿⣿⣿⣿⣯⣙⢷⡄⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣿⣻⢿⣷⣀⣷⣄⠀⠀⠀⠀⢸⠀" + System.lineSeparator() +
                "⢸⠃⠠⣼⠃⠀⠀⣠⣾⡟⠀⠈⢿⣿⡿⠿⣿⣿⡿⠿⠿⠿⠷⣄⠈⠿⠛⠻⠶⢶⣄⣀⣀⡠⠈⢛⡿⠃⠈⢿⣿⣿⡿⠀⠀⠀⠀⠀⡀" + System.lineSeparator() +
                "⠟⠀⠀⢻⣶⣶⣾⣿⡟⠁⠀⠀⢸⣿⢅⠀⠈⣿⡇⠀⠀⠀⠀⠀⣷⠂⠀⠀⠀⠀⠐⠋⠉⠉⠀⢸⠁⠀⠀⠀⢻⣿⠛⠀⠀⠀⠀⢀⠇" + System.lineSeparator() +
                "⠀⠀⠀⠀⠹⣿⣿⠋⠀⠀⠀⠀⢸⣧⠀⠰⡀⢸⣷⣤⣤⡄⠀⠀⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡆⠀⠀⠀⠀⡾⠀⠀⠀⠀⠀⠀⢼⡇" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠙⢻⠄⠀⠀⠀⠀⣿⠉⠀⠀⠈⠓⢯⡉⠉⠉⢱⣶⠏⠙⠛⠚⠁⠀⠀⠀⠀⠀⣼⠇⠀⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀⡇" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠻⠄⠀⠀⠀⢀⣿⠀⢠⡄⠀⠀⠀⣁⠁⡀⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⢀⣐⡟⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⢠⡇";
    }

    public static String sakuraAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⢣⠀⠀⢧⠀⢣⠀⠀⠀⠀⢰⠀⣂⠀⠀⠀⣀⠀⠈⢣⠀⠀⠀⢣⠱⣄⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈" + System.lineSeparator() +
                "⠀⡅⠀⠀⠀⠀⠀⠀⠀⠀⡇⢀⣣⠀⠘⡄⠈⢆⠀⠀⠀⠸⡀⠀⠀⢠⣾⣿⣧⡀⠀⢣⠀⠀⢸⠀⠈⣧⡀⠀⠀⠀⠀⣠⠂⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠘⠗⢣⡀⢳⠀⠈⢧⠀⠀⠀⡇⠀⠀⠘⢿⣿⠟⠁⠀⠀⢣⠀⠀⡄⠀⠀⠹⣄⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡧⠤⢄⣀⠑⡌⣇⠀⠈⢣⡀⠀⢳⠀⠀⠀⠀⠁⠀⠀⠉⠀⠀⢧⠀⡇⠀⠀⠀⠙⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢸⠀⠀⠀⠀⠀⠀⠀⠄⠀⡇⠀⠀⠀⠉⠛⢾⣦⣀⡀⠳⡀⠸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⣇⣷⣴⠶⠒⠛⠉⢷⡀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⣠⣔⣶⣖⣒⣤⣻⡍⠛⠷⢿⡄⢻⠀⠀⠀⠀⠀⠙⢶⣶⡟⠋⢙⣿⣶⣦⣭⣿⣋⣦⢷⡀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡟⠉⡽⢉⡉⠻⣿⣦⠀⠀⠘⢮⡆⠀⠀⠀⠀⢠⡾⠋⠀⣴⠿⠟⢉⡉⢫⠙⣿⡟⠁⠱⡀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⢧⠀⢧⡈⠉⣠⠃⠹⡷⠀⠀⠀⠻⡄⠀⠀⠀⢸⠁⠀⣼⠏⠀⢦⣈⣠⠎⣴⠏⠀⠀⠀⢳⡀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⣁⠀⠀⠀⠀⠐⢄⠀⠀⠀⠘⣯⡿⠶⢾⠿⣶⡶⠿⠃⠀⠀⠀⠀⠙⠂⠀⠀⠚⠀⠀⠛⠻⠶⠶⠶⠒⠛⠙⠃⠀⠀⠀⢀⣷⡀⠀⠀⠀" + System.lineSeparator() +
                "⠀⡟⡆⠀⠀⠀⠀⠈⠣⡀⠀⠀⠹⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠛⢳⡀⠀⠀" + System.lineSeparator() +
                "⠀⠁⢸⡀⠀⠀⣴⠀⠀⠙⢆⠀⠀⢣⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⢰⠃⣠⣫⣧⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⢣⠀⠀⠛⠀⠀⠀⠀⢳⣄⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⢀⣿⣿⣽⣿⣿⢇⠀" + System.lineSeparator() +
                "⢸⠀⠀⠈⢧⠀⠀⠀⠀⠀⠀⠘⡆⠑⢄⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⢫⣿⣿⣷⠼⡆" + System.lineSeparator() +
                "⢸⠀⠀⠀⠈⢦⠀⠀⠀⠀⠀⠀⠸⡀⠀⠙⠻⡄⠀⠀⠀⠀⠀⠐⢶⣶⣤⣤⣶⠖⠀⠀⠀⠀⠀⠀⠐⠂⠀⠀⢀⣾⢋⣼⣾⣿⡿⡫⠊⢹" + System.lineSeparator() +
                "⠈⠀⠀⠀⠀⢈⣧⠀⠀⠀⠀⠀⠀⢱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣔⡱⠋⠀" + System.lineSeparator() +
                "⠀⡀⠀⠀⣴⣿⣾⣧⡀⠀⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣾⣽⣶⣿⢿⣿⡿⢧⠀⠀" + System.lineSeparator() +
                "⠀⡇⠀⢀⡏⠛⠻⠿⣷⡄⠀⠀⠀⠀⠈⢆⠀⠀⠀⠀⠀⠀⡀⠤⠴⠤⠤⠤⠶⠦⠤⠄⠀⠀⠀⠀⢠⣾⣿⢿⣿⠟⠁⣠⢪⠞⠀⣼⠀⠀" + System.lineSeparator() +
                "⠀⠇⠀⢸⠀⢼⡄⠀⣼⢹⢆⠀⠀⠀⠀⠈⢆⠀⠀⠀⠀⠀⠀⠀⠀⠒⠒⠒⠒⠒⠀⠀⠀⠀⢀⠴⣿⣿⣿⣿⠃⠀⣴⡇⡇⠠⣼⣿⠀⠀" + System.lineSeparator() +
                "⠀⢸⠀⢸⠀⣼⡇⠀⢹⢸⠈⣷⣀⠀⠀⠀⠈⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣯⣾⣿⣿⣿⣿⠀⢼⠿⠇⡧⠀⣿⣿⠀⠀";
    }

    public static String yumekoAscii(){
        return "⣿⣿⣿⣿⣿⣿⡿⠋⠀⣰⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⣿⡟⣡⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⢯⡴⢣⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⣿⡟⢠⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⣿⠏⣴⡿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣿⣿⣿⡟⢸⣿⣿⢿⣿⣿⣿⣿⣿⣿⡄⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⣿⠏⡼⢻⣼⣿⣿⣿⣿⣿⣿⣿⣿⡇⢻⣿⣿⣿⣿⣿⣿⠇⠀⠀⠟⣫⡵⠶⠛⠿⡿⣶⣌⣿⣿⣿⣿⣿⣷⡀⠀⠣⡀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⣿⡟⣼⠁⣾⣿⣿⣿⣿⣿⢻⣿⣿⣿⣇⣈⣹⡿⠛⠛⠉⠁⠀⠀⠀⡼⡿⠧⠴⠦⠤⠧⠼⠛⣿⣿⣿⣿⣿⣿⣿⡄⠀⠱⡀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⡟⣸⠏⠀⣿⣿⣿⣿⣿⣿⡀⢻⣿⡿⠛⠋⠉⣻⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⠘⢆⠀⠐⡄⠀⠀⠀⠀" + System.lineSeparator() +
                "⢁⡏⠀⠀⣿⣿⣿⣿⣿⣿⣧⣿⠋⣧⠤⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢐⡟⢹⡇⣿⣿⣿⣿⣿⡇⠈⢧⠀⠘⡄⠀⠀⠀" + System.lineSeparator() +
                "⢸⠋⠀⠀⣿⣿⣿⣿⣿⣿⣿⠁⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⣼⠁⣿⣿⣿⣿⣿⡇⠀⠈⢧⠀⠱⠀⠀⠀" + System.lineSeparator() +
                "⠚⠇⠀⠀⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠃⠀⣿⣿⣿⣿⣿⣧⠀⠀⠀⢳⡀⠁⠀⠀" + System.lineSeparator() +
                "⢶⡏⠀⠀⣿⣸⣿⣿⣿⣿⡿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠄⢀⣀⣠⣄⡠⠄⠀⠀⠀⠀⢰⠋⣿⣿⣿⣿⣿⠀⠀⠀⠀⠹⡄⠀⠀" + System.lineSeparator() +
                "⠰⠋⠀⠀⢹⣿⣿⣿⣿⣿⡿⣜⣄⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⠾⠛⢋⣩⡿⠉⠀⠀⠀⢀⠀⠀⣸⢛⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠹⡄⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⢈⣿⣿⣿⣿⣿⣷⡈⠻⢆⠀⠀⠀⠀⠀⠀⠀⠈⠉⡽⢍⡟⠏⠀⠀⠀⡰⠉⡏⠀⣠⣧⢼⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠹⡄" + System.lineSeparator() +
                "⢀⠀⠀⠀⡼⢻⣿⣿⣿⣿⣿⠙⠢⣍⣳⡀⠀⠀⠀⠀⠀⠀⠀⢇⠈⡄⠘⡄⠀⢰⠁⢠⢁⣴⠟⢀⣾⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠹" + System.lineSeparator() +
                "⠀⠀⠀⢠⠇⠈⣿⣿⣿⣿⣿⣇⠀⠀⠀⠙⠢⣀⠀⠀⠀⠀⠀⠈⠣⡱⡀⠘⣄⢸⠀⠐⣿⠋⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⢀⠏⠀⢰⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠙⢶⠦⣤⣄⣀⣀⣘⣧⠀⠈⢾⠀⠀⡇⠀⢰⣿⣿⣿⣿⣿⡇⢹⣿⣿⡇⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⢀⡞⠀⠀⣾⣿⠹⣿⣿⣿⣿⣿⡀⠀⠀⠀⢶⠀⣼⢆⣀⠉⠙⠛⠛⠛⢣⡀⢸⠀⠀⡇⠀⣼⣿⣿⣿⣿⡿⠁⠀⢿⣿⣿⡀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢀⡞⠀⠀⣸⣿⡏⠀⠙⣿⣿⣿⣿⣷⡀⠀⢰⣼⣰⡇⠀⠀⠉⠒⠢⢄⣀⣀⣵⣼⠀⠀⢡⢰⣿⣿⣿⣿⠛⠁⠀⠀⠈⢿⣿⣧⠀⠀⠀⠀" + System.lineSeparator() +
                "⠋⠀⠀⣰⣿⡟⠀⠀⠀⢸⣿⣿⣿⣿⣿⣶⣿⣿⣿⠀⢰⠋⠒⢄⠀⢀⣴⠉⣾⡇⠀⠀⠈⡏⠸⣏⠉⠁⠀⠀⢸⣦⡀⠈⢿⣿⣧⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⣰⣿⣿⢠⢈⣡⣴⣿⠗⢉⢿⣟⣿⣿⣿⣿⡏⠀⠀⢣⡀⢀⣳⣧⣬⢅⣿⠃⠀⠀⠀⢸⠀⢻⣷⣤⣀⣤⣼⣿⣷⣄⣸⣿⣿⣧⠀⠀" + System.lineSeparator() +
                "⢀⣼⣿⣿⣾⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⣱⡟⠋⠹⢿⠏⡘⠀⠀⠀⠀⡜⠀⠸⣿⣿⣿⡿⠿⣿⣿⣿⣿⣾⣿⣿⣷⡀" + System.lineSeparator() +
                "⠿⠛⠋⠉⠉⠁⠀⠀⠈⠉⠁⢠⣠⣿⣿⣿⣿⡿⠤⠤⠖⠁⣠⣷⠀⠀⠀⠠⠃⠀⠀⠀⢠⡗⢄⣀⣻⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠈⠉⠙" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⠀⠀⢀⣴⣾⣿⣿⡆⠀⠀⠁⠀⠀⠀⠀⢸⣧⣄⠀⠘⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⡇⢤⣾⣿⣿⠿⠋⠁⠙⣄⠀⠀⠀⠀⠀⠀⢸⠿⣿⣷⣤⣹⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀";
    }

    public static String mitsuriAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡖⠶⣤⣤⡤⠤⠤⠤⢤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣼⠁⡰⠁⣠⠉⠓⠸⠠⢒⣫⣽⡶⠶⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠞⣉⠤⢾⠀⠃⣸⠁⡄⠀⡇⠘⠋⣀⠈⠻⡷⡌⠳⡿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⢋⡔⠉⡠⠤⣼⡼⠀⡇⢰⠁⢰⠁⣞⣉⣉⠑⢦⡘⢿⢦⠘⠎⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡞⠁⢀⡮⠖⣡⠔⠋⢹⠇⢰⠀⣼⠀⢸⢠⡏⠉⠙⠿⣟⣻⣎⢣⠑⣆⠀⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⢠⡟⠁⡏⠀⣰⠋⣠⠞⠁⠀⠀⣼⠀⢸⠀⡇⠀⠘⢸⠀⠀⠀⠀⠀⠙⢿⣦⢧⢸⡄⠀⢻⣦⠀⠀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⠀⢸⠃⢸⠀⡰⠁⡴⠁⠀⠀⢀⣠⣾⡆⢸⠀⡇⠀⡇⢸⠐⢶⣤⣀⡀⠀⠀⠙⣿⢺⡇⠀⠐⡏⢷⡀⠀⠀⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⠀⠀⢀⣾⠀⠈⢰⠃⡼⠁⠀⠀⠾⠛⠋⠀⢻⣼⠀⣧⠀⣧⢸⠀⠀⠈⠙⠛⠂⠀⠀⠸⣶⡇⠀⠀⢣⠈⢿⠳⣄⠀⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⠀⢀⣴⢻⢿⠀⠀⡏⣸⠁⠀⠀⢀⣀⠀⠀⣀⠈⢿⠀⢸⠀⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡇⠀⡄⢸⠀⠘⡆⠈⠳⣄⠀⠀" + System.lineSeparator() +
                "⠀⠀⣠⢾⢃⣏⣾⠀⡜⠁⡏⠀⡠⢚⣉⣀⣤⣄⣈⠑⠺⣇⢸⡆⠿⣿⠀⠒⠉⠀⡀⢀⡖⠲⢤⡀⡇⠀⣇⠘⡆⠀⢻⡇⠀⠞⡆⠀" + System.lineSeparator() +
                "⠀⣼⡿⠛⣸⠏⢸⠀⠂⠀⡷⣋⣰⣾⠿⠟⠛⠛⠻⣷⡄⢮⠀⣧⡆⢿⠆⢀⣴⠾⠿⠿⢷⣶⣶⣯⣧⠀⡿⠀⡇⠀⡸⠀⡼⢠⠇⠀" + System.lineSeparator() +
                "⠀⢹⡗⠚⠁⣠⢿⡀⢠⢸⣧⣽⡿⢁⡔⠋⠉⠉⠓⣆⠙⠾⣧⠸⣧⠈⠀⠉⠀⡠⠖⠒⠶⢬⡙⢿⣿⡄⡇⢸⠃⢰⠃⡰⢁⡞⠀⠀" + System.lineSeparator() +
                "⠀⠀⠳⣤⠞⠁⠀⣧⢸⣸⡿⡟⠀⡞⠀⢀⠀⠀⠀⠈⣧⠀⠘⢧⠻⡄⠀⢠⠎⠀⠀⠀⠀⠀⠙⢶⢻⠁⣇⡎⠠⠃⢰⣡⠏⠀⠀⠀" + System.lineSeparator() +
                "⠀⠀⢀⡼⣦⠤⢤⡼⣾⣿⡇⠃⠀⠇⠀⠿⠿⠿⠃⠀⡼⠀⠀⠀⠉⠃⠀⠘⠀⠰⠿⣷⡶⠂⠀⢸⢹⣶⣟⡤⠄⠀⠸⢧⣄⡀⠀⠀" + System.lineSeparator() +
                "⠀⣠⣏⠀⢻⢀⠎⠀⣿⣿⠃⠀⠀⠙⠦⢤⡤⠤⠴⠚⠁⠀⠀⠀⠀⠀⠀⠐⢦⣀⠀⠀⠀⢀⡠⢋⣼⡟⠉⡇⠀⢀⠀⠀⠈⠻⣦⡀" + System.lineSeparator() +
                "⣰⡇⠹⡄⢈⡇⠀⡜⢀⡟⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠒⠊⠉⣠⢾⠁⢧⠀⢃⠀⣠⠄⢀⠆⠀⠘⡇" + System.lineSeparator() +
                "⡏⢳⡀⢳⠀⢸⡘⠀⡜⢠⢻⢀⡤⠊⠔⡧⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣖⠔⢻⠄⠀⣰⡇⠸⡄⠘⡆⠘⠞⢁⡴⠃⠀⠀⢰⠇" + System.lineSeparator() +
                "⢻⡄⢳⡀⢣⠀⢧⠎⢀⠃⢸⡀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⢸⠀⢣⠀⢱⠀⠐⠋⡠⠀⡀⢀⡞⠀" + System.lineSeparator() +
                "⠀⠹⣆⠑⠀⠃⠈⠀⠎⣠⠋⢳⡀⠀⠀⠀⠀⠀⠀⠈⠓⠦⣄⣠⠤⠚⠁⠀⠀⠀⠀⠀⢸⡇⠈⣇⠈⢇⠀⠣⠒⠋⣠⠎⢠⠞⠀⠀" + System.lineSeparator() +
                "⠀⠀⠈⠻⣶⣄⡤⠖⢋⡥⠂⠈⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⡀⠘⣆⠈⠀⠀⠠⠞⢁⡴⠃⠀⠀⠀" + System.lineSeparator() +
                "⠀⢀⡴⠒⢏⠙⢆⠚⠁⣀⠤⠂⠙⢿⢦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⣾⢻⠷⣄⡀⠀⣀⠤⢠⡶⣯⣀⠀⠀⠀⠀" + System.lineSeparator() +
                "⢀⣾⠻⡄⠀⠳⣌⠻⡉⣀⠤⠀⢀⣼⠀⠉⠛⠦⣤⣀⡀⠀⠀⠀⠀⢀⣀⣠⣴⣾⠏⡰⢃⣎⣤⠚⣏⠉⢁⠔⠉⢀⠔⠙⣷⠀⠀⠀" + System.lineSeparator() +
                "⢸⡟⢆⠙⣄⠀⠈⠣⡌⠁⢉⣠⠞⠁⠀⠀⠀⣠⡏⠉⠙⠛⠒⠒⠚⠉⠁⠀⠸⣿⣾⣤⡟⠋⠸⡄⠸⡖⠉⢀⠔⠁⢀⠀⢸⠀⠀⠀" + System.lineSeparator() +
                "⠀⠙⣮⣳⡈⠓⠄⠀⠀⠉⠛⠶⣄⠀⣀⣴⣊⣵⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡹⠟⡿⡆⢸⠀⢧⠀⢱⠔⠁⢀⡴⠋⢀⡟⠀⠀⠀" + System.lineSeparator() +
                "⢠⡞⣁⢻⡓⠚⢷⡀⠀⡠⠂⠁⠈⠻⡅⢸⠟⢺⠏⠀⠀⠀⠀⠀⠀⠀⣠⠖⠉⣠⢾⠁⢳⠈⡇⠈⣆⠀⠷⠒⡁⠀⢠⡞⠀⠀⠀⠀" + System.lineSeparator() +
                "⢸⡄⠘⣄⠙⣄⠀⠳⣎⣠⠔⠁⠀⠀⡅⠘⣇⢸⣆⠀⠀⠀⠀⢀⡴⠋⣀⠴⠚⡆⢸⠀⠀⢧⡘⠄⠈⣂⠀⠈⢀⠴⠋⠀⠀⠀⠀⠀";
    }


}

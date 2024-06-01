package server.service;

import commons.Character;
import commons.Rarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import server.database.CharacterRepository;

import java.util.ArrayList;
import java.util.List;

import static commons.Rarity.*;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;


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
    public CharacterService(CharacterRepository characterRepository){this.characterRepository=characterRepository;}

    public void seedDatabase(){
        for(Character character : possibleCharacters){
            this.characterRepository.save(character);
        }
    }


    public boolean exists(long code) {
        return characterRepository.existsById(code);
    }
    public static String raidenAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⣤⠞⠛⣻⣧⣷⣾⣿⣿⣿⣿⣶⣶⣯⣤⣀⡀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢀⢠⠀⠀⠀⢔⣾⣷⠟⠁⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣭⡢⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢀⢴⣿⣯⡻⣟⣵⣿⣿⣁⠨⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⣽⡿⣹⣾⠷⡿⢿⣿⢿⣿⠀⡄⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣦⡠⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢀⣼⠿⣽⣿⠁⢎⡀⢌⣠⠏⢻⡆⢸⣿⣿⢿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣻⢿⣿⣿⣿⣿⣷⡠⠀⠀⠀⠀⠀⠀\n" +
                "⢸⣯⢿⣿⡿⡀⣘⠎⠙⢶⡀⠌⣿⠑⣿⣯⢿⡿⣿⣿⣯⣟⡿⣿⣿⡿⣿⣽⣟⡿⣭⣟⣾⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀\n" +
                "⢸⢯⣿⣿⣷⡑⠑⠲⡄⡖⠛⠒⣼⣿⣿⣽⣯⣿⣿⣿⡷⣯⣿⣽⣳⢿⣽⣻⣾⣽⣿⣿⣿⣾⣿⣿⣿⣿⣧⠂⠀⠀⠀⠀\n" +
                "⢸⣯⣿⣿⣿⠿⣧⣆⠻⣄⣶⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀\n" +
                "⠘⣷⣿⡙⠁⢀⣻⣿⣿⠛⠻⢿⣿⣿⣿⣿⡿⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀\n" +
                "⠀⢽⣥⣷⠂⡀⢠⣿⣯⣑⣠⣾⣿⣿⣿⣿⠃⠀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣣⠀⠀⠀\n" +
                "⠀⠀⠙⣿⣿⢿⣶⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣽⣿⣿⣿⣿⣿⣿⣿⣿⣻⠀⠀⠀\n" +
                "⠀⠀⢰⣿⡏⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣗⠀⣀⣀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣯⠀⠀⠀\n" +
                "⠀⠀⣿⠇⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠉⠀⠀⠀⠈⠻⢿⣯⡻⢿⣿⣟⣿⣿⣿⠿⠿⠿⠟⢻⣿⣿⣿⣿⣿⢇⠀⠀\n" +
                "⠀⢰⡟⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡛⠿⣆⠀⠀⠀⠀⠀⠀⠉⠁⠀⠉⠉⠉⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣟⣿⣯⠆⠀\n" +
                "⠀⠺⣧⣄⣠⣿⣿⡿⠛⠻⣿⡿⣿⣿⡇⢀⣠⣤⣤⣤⣅⣒⠀⠀⠀⠀⠀⠀⠀⣮⣶⡾⢿⢷⠖⣿⣿⣿⣿⣿⡹⣿⣧⠀\n" +
                "⠀⠀⢸⣿⣿⣿⡿⠠⠤⣄⡈⠧⢿⣿⣯⠛⠛⠛⠉⠉⠉⠙⠀⠀⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⡜⣿⡆\n" +
                "⠀⢠⣿⣿⣿⣿⣿⣄⠀⠘⠮⠖⠈⢿⣿⣧⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⣿⡿⣽⣿⣸⠇\n" +
                "⠀⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣤⣶⣶⣽⣻⢿⣷⠦⠀⠀⠀⠀⢄⣀⣀⡀⠀⠀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⡿⠀\n" +
                "⠘⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣦⣤⣤⣀⣀⡀⣠⠞⢉⣶⡤⢤⣴⣶⣿⣿⣿⣿⣿⣿⣿⣿⠿⠋⠀⠀\n" +
                "⠀⣻⣿⣿⣿⣿⡻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣯⣵⣶⣟⣳⢶⡄⠈⢻⣿⣿⣿⡿⣿⢿⠅⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠙⢿⣿⣿⣿⣷⣾⠯⣿⣿⣿⣧⣀⠀⠀⠈⠙⠛⠿⢿⡿⣿⣏⣁⣀⣉⣙⣣⠀⣠⣿⣿⣿⣷⣿⣭⣶⣤⡆⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠈⠉⠉⠉⣱⣻⣿⣿⣿⠋⠚⠻⠭⠷⣲⢤⣀⠀⠊⠛⢤⡈⣻⠋⠈⡽⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⠎⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢀⢰⣿⣿⣿⡿⠁⠀⠀⠀⢀⣠⣶⠞⠘⠯⡲⣄⣀⣽⣅⣀⡞⠀⢹⣿⣿⣿⣿⣿⡿⣿⡿⡅⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣠⣿⣾⣿⣿⡿⠁⠀⠀⠀⠀⠉⣼⣯⣽⣶⣴⣶⣿⣿⣿⣿⣻⣻⢷⣾⡟⢿⣿⣯⣷⡿⣿⣿⠋⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠛⠿⢿⣿⣛⡶⣤⣀⣀⣀⣀⣀⣿⣿⣷⣿⣲⣌⡻⣛⣿⣿⣿⣿⡿⠟⡻⣿⣷⣿⣿⣧⣸⡇⠀⠀⠀⠀⠀";
    }

    public static String nezukoAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣬⣿⣿⣿⣿⣿⣿⣽⣿⣿⣭⣭⣅⡀⠀⠀⠈⠀⢐⠙⠓⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⡿⠿⠿⠿⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⡌⠀⠀⠀⢀⡽⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⣿⣟⣭⣶⣿⣿⣿⣿⣿⣶⣯⣿⣿⣿⣿⣞⢿⣿⣿⣶⠁⠀⠀⣠⠞⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣶⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⡿⠟⠁⣠⡞⠁⠀⠀⠀⣀⣤⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⣿⣾⣿⡿⠛⠉⠛⠉⠁⠀⠀⠀⠀⠀⠙⠛⠻⠿⣿⣿⣿⣟⡟⢦⠤⠺⠿⢿⡤⠴⠚⠉⠁⣾⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⡿⠋⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠻⢿⣶⣧⣀⠀⠀⠀⠀⠀⠀⠀⢠⡇⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣿⢿⣶⣤⣤⣤⣤⠤⠟⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣾⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⡏⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⠁⠀⢀⣀⢀⣀⡀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡠⠀⣠⣀⡀⠀⠀⠀⠀⣿⣿⣿⢹⣿⣿⣽⣿⡇⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣾⡿⣿⡇⠀⢀⣥⣤⣶⣶⣬⣁⠀⠀⠀⠀⠀⠀⢹⣿⠶⠶⢶⣤⣭⣑⠢⡀⠀⣿⣿⣿⣿⣿⣿⣿⢿⣿⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣿⣿⣿⣇⣼⠟⢉⡠⠄⠲⢄⠉⠓⠀⠀⠀⠀⠐⠁⣠⠔⠂⠒⠢⣍⠻⣷⣄⣀⣿⣿⣟⣿⣿⣿⣿⡾⣿⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⣿⣿⣾⣯⠃⣸⠋⡤⠀⡆⠀⠱⡄⠀⠀⠀⠀⠀⣾⠡⣋⣀⡆⠀⠈⣧⠈⢻⡌⣿⣿⣿⡏⠻⣿⣿⣿⢿⡄⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⠀⢿⡀⠈⠿⡇⠀⢀⡇⠀⠀⠀⠀⠀⣇⠀⠉⠛⠃⠀⠀⡟⠀⠀⠀⣿⣿⣿⣏⠂⢹⣿⣿⣾⡇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⡆⣼⠣⠀⠀⠀⢀⡼⠁⠀⠀⠀⠀⠀⠙⠤⢀⡀⠀⡀⢜⣁⠀⠀⠀⣿⣿⣿⡏⠁⢸⣿⣿⣿⣧⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢨⣿⣿⠟⢻⣇⡿⠷⡂⠈⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣈⣩⠴⣟⣁⣀⠀⢰⣿⣿⣿⡇⢀⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⣾⡿⠁⢠⠏⡸⠀⢰⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⠉⡿⠉⡼⢩⠃⢠⠈⣇⣸⣿⣿⣿⣶⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢰⣿⡇⠀⠈⠀⡇⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠀⢸⠀⡎⠀⣼⣿⡿⣿⣿⣿⣿⢹⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣾⣿⣷⡀⠸⣶⢳⡀⢳⣤⣤⣤⣀⣀⠀⠀⠀⠀⠀⠂⠀⣯⠀⢸⣤⣧⠀⣿⢿⣾⣿⣿⣿⣿⣼⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⣿⣿⣿⣿⣿⣿⣿⡿⢟⣿⣿⣿⠛⠛⢿⡿⠉⠹⠿⠿⠾⠛⣦⠼⠛⠿⣦⣬⣿⣯⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀\n" +
                "⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠱⣄⠀⠀⢀⠔⠋⠀⠀⢀⣴⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠹⣿⣿⣿⣆⠀⠀\n" +
                "⠀⠀⢀⣿⣿⣿⡿⢛⣵⡿⣿⣿⣿⠟⠁⢧⠀⠀⠀⠀⠈⣢⠞⠁⠀⠀⠀⡰⠋⢀⣰⣿⣧⣿⣿⣿⣿⣸⣿⣿⣯⡻⣿⡇⢻⣿⣿⣿⣆⠀\n" +
                "⠀⢀⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⠜⢁⣸⣇⠀⢀⡠⠊⠁⠀⠀⠀⣠⠊⣠⠔⠉⢀⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠈⣿⣿⣿⣿⣆\n" +
                "⣴⣿⣿⣿⢟⣽⣿⣿⣿⣿⣿⡿⣣⠔⣩⠛⡏⢷⠋⠀⠀⠀⠀⡠⣎⠉⠁⢱⠀⠀⢸⢻⡿⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⡄⢹⣿⣿⣿⣿\n" +
                "⣿⣿⣿⣫⣿⣿⣿⣿⣿⣿⣿⠟⠁⡴⠁⢸⠀⠈⠣⠀⣀⣴⠋⡙⢌⠳⡀⠀⢇⠀⡏⢸⣿⡹⣿⣿⣿⣧⣻⣿⣿⣿⣿⣿⣷⡀⣿⣿⣿⣿\n" +
                "⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⡤⠎⠀⠀⢸⠀⣀⡴⠋⡱⠃⢠⠁⠈⠢⡘⢆⠸⠀⠇⢸⣿⣷⣽⣿⣿⣿⣧⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿";
    }

    public static String vendyAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⢻⡀⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⣻⠋⠀⠀⣇⠸⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣤⣴⣶⣶⣾⣿⣿⣿⣿⣶⣿⣦⣥⡀⠀⠀⣸⡀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⡋⢹⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣠⣤⠤⣀⡀⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢿⣿⡄⠀⠈⠉⠑⠒⠚⠛⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠘⣿⣷⡀⠀⠀⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠹⣿⣧⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⢿⣿⣿⣿⣿⣧⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠹⣿⡷⠦⣄⡀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡜⣿⣿⣿⣿⣿⡞⣿⠻⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢀⣾⣿⣄⠀⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢻⣿⣷⢻⣿⣿⣿⣿⣷⣿⣷⠚⠻⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀\n" +
                "⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢀⣻⣿⣧⣿⣿⣿⣿⣿⣼⣿⡄⠀⣈⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀\n" +
                "⠀⢠⣿⣿⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠙⢿⣿⣏⠻⣿⣿⣿⣿⣿⣾⢻⡀⠙⣿⣿⣿⣿⣿⠀⠀⠀⠀\n" +
                "⠀⢸⣿⣿⣻⣿⣧⢹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⢀⣀⣐⣻⣿⣆⠘⠟⠿⣿⡿⣿⣿⣧⠀⠿⣿⣿⣿⣿⡇⠀⠀⠀\n" +
                "⠀⣼⣿⣿⣻⣿⣿⡀⠹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣾⣿⡉⣆⠉⠃⠙⠂⠀⠈⠃⠹⣿⡿⢀⠀⣿⣿⣿⣿⣇⠀⠀⠀\n" +
                "⠀⣿⣿⣿⣿⣿⣿⣇⠀⠘⢿⣿⣿⡿⠿⢿⣿⣿⣿⣿⡇⢻⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠚⠩⠒⣿⣿⣿⣿⣿⠀⠀⠀\n" +
                "⠀⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠙⠿⣇⠖⠾⢿⣿⣿⣿⣧⠀⠻⠿⠿⢁⠀⠀⠀⠀⠀⠀⠃⠀⠀⠀⢠⣿⣿⣿⣿⣿⠀⠀⠀\n" +
                "⠀⣿⣿⣿⡈⣿⣿⣿⣧⠀⠀⠀⠀⠈⢣⡐⠼⣿⣿⣿⣿⡄⢀⢒⡚⠉⠀⠀⠀⠀⠀⠀⢀⡀⠀⢠⣿⣿⣿⣿⣿⣿⡀⠀⠀\n" +
                "⠀⣿⣿⣿⡇⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠈⠒⠚⢿⣿⣿⣷⡄⠈⠀⠀⠀⠀⣀⠀⢀⡠⠋⠀⣴⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀\n" +
                "⢸⣿⣿⣿⡇⢻⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠈⢿⣿⣿⣿⣦⢤⣀⣀⡀⠈⠉⠉⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀\n" +
                "⢸⣿⣿⣿⡇⢸⣿⣿⣿⣿⡇⠀⣀⣀⣀⡀⠀⠀⠀⢸⢻⣿⣿⣿⣦⠀⠀⠈⠉⣏⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀\n" +
                "⢸⣿⣿⣿⣿⢸⣿⣿⣿⣿⡿⠋⠁⠀⠀⠉⠉⠓⣶⠜⠳⣿⣿⣿⣿⣷⣦⣤⣾⣭⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀\n" +
                "⣾⡇⣿⣿⣿⠀⣿⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠑⢦⡈⣿⣿⣿⣿⣿⣿⣿⣟⠻⡉⠙⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⠀⠀\n" +
                "⣿⡇⣿⣿⣿⡇⢻⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠙⢻⣿⣿⣿⣿⣿⣿⣏⢷⣌⠻⡁⠀⠀⠹⣿⣿⣿⣿⣿⢹⠀⠀\n" +
                "⣿⣇⣿⣿⣿⡇⠸⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⠄⢸⣿⣿⣿⠻⣿⣿⣿⣯⢿⣧⠙⢆⣄⠀⢹⣿⣿⣿⣿⢸⡇⠀\n" +
                "⣿⣟⢻⣿⣿⣿⠀⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⢸⣿⣿⣿⡆⢻⣿⣿⣿⣟⠛⠷⡈⣿⣦⡀⢿⣿⣿⣿⣆⣇⠀\n" +
                "⣿⣿⢸⣿⣿⣿⡆⢸⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠈⡇⢸⣿⣿⣿⣧⠘⢻⣿⣿⣿⣄⣤⣴⣿⣿⣿⣾⣿⣿⣿⡇⢿⠀\n" +
                "⠿⠿⠼⠿⠿⠿⠷⠬⠿⠿⠿⠿⠿⠷⠆⠀⠤⠴⠶⠿⠿⠿⠿⠿⠿⠿⠿⠷⠤⠾⠿⠿⠷⠘⠿⠿⠿⠿⠿⠿⠿⠿⠷⠼⠄";
    }

    public static String boaAscii(){
        return "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣿⣿⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀\n" +
                "⣿⡿⣿⡿⣿⣾⣿⣿⡿⣻⣾⣿⡟⣿⣿⣿⣿⢹⣿⣿⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⡿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣧⣿⣿⣟⣷⣿⣿⣿⣷⣿⣿⣿⣷⣾⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣷⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣟⣺⣛⣻⡷⢶⣶⣆⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣔⣚⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣿⣶⣌⠁⠀⠀⠀⠀⠀⠀⡰⢯⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠈\n" +
                "⣿⣿⣿⣿⣇⡙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠈⣙⠧⠀⠀⠀⠀⠀⠀⢸⢁⡔⢻⣯⣽⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀\n" +
                "⣿⣿⣿⣿⠁⣩⣹⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⡟⠛⠛⠛⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⢾⠀⠈⠉⠙⠛⢻⣿⣿⣿⣿⣿⣿⣿⣿⣰⠃⠀⠀\n" +
                "⣿⣿⣿⣿⡀⢣⠸⣿⣿⣿⣿⣿⣿⣿⣿⡿⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡆⠀⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣷⣌⠳⣤⣻⣿⣿⣿⣷⡏⣸⢣⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣷⠀⠀⠀⢠⣿⣿⣿⣿⣿⣿⣿⣿⣼⠀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣷⡦⣿⣽⣿⢿⣿⠁⣿⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡤⠖⠉⠀⠀⠀⣼⣿⣿⣿⣿⣿⣿⢻⠏⢸⠀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⡟⠛⣿⣹⣷⣄⠘⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣧⡟⠀⢸⠀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⣿⠀⢀⣿⣥⠈⢹⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠠⣶⠾⠯⠤⠤⢼⠃⠀⠀⠀⠀⣠⣾⣻⣿⢸⣿⡿⠈⠀⠀⢸⠀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⡇⠀⣸⢹⣷⣶⡞⢿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠈⠉⠒⠒⠈⠁⠀⠀⠀⢀⣼⠃⣾⣹⣿⡿⠀⠀⠀⠀⠀⠈⣇⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⠁⢀⣿⢰⡏⠉⠀⠀⠙⢿⣿⣿⣿⣷⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⡇⢰⣿⡏⣿⡇⠀⠀⠀⠀⣰⣆⣼⡇⠀⠀\n" +
                "⣿⣿⣿⣿⣿⣿⠀⢸⣿⣸⢷⠀⠀⠀⠀⠀⠙⢿⣿⣿⣿⣿⣿⣶⣤⣄⣀⣀⣠⣤⣾⣿⣿⡿⠀⢸⣿⡇⢼⣇⠔⡹⣳⡶⢩⡿⢿⡅⠀⠀\n" +
                "⣿⣿⣿⣿⣿⡟⠀⣾⣿⣿⣻⠀⠀⠀⠀⠀⠀⠀⠻⣿⣸⡇⣿⣿⣷⣿⣿⣻⣿⣿⣿⣿⣿⡇⠀⣿⣿⣿⣶⡿⠞⠾⠏⠀⡸⠓⣯⠿⡄⠀\n" +
                "⣿⣿⣿⣿⣿⣇⠀⣿⣿⡿⣼⡄⠀⠀⠀⠀⠀⠀⠀⠘⣿⣿⣿⣿⣸⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⣿⣿⡿⢬⡇⠀⠀⣤⣶⠁⢰⠃⠀⠹⣄\n" +
                "⡻⣿⣿⣿⣿⣿⠀⢿⣿⢘⣻⡇⠀⠀⠀⠀⠀⠀⠀⠀⠸⢻⣏⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠘⣿⣿⡿⢼⡇⠀⢀⢻⣿⠆⠁⠀⠀⠀⠈\n" +
                "⠉⠻⣯⠻⡟⣿⡀⠘⠛⠨⢽⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠛⢿⣹⢿⣿⣿⣿⣿⣿⣿⣿⡇⠀⣿⡏⣙⣺⡇⠀⡞⠘⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠘⣾⣷⣲⡰⡉⠛⠳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⡇⠀⠉⢰⢤⣿⣃⠜⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠙⠋⠁⠱⡄⠦⠘⣶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣷⣄⠀⢛⣾⡿⠋⡔⠁⠀⠀⠀⠀⠀⠀⠀⠀";
    }


    public static String ayanamiAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⣤⠤⠤⣤⣷⣎⣠⠴⠒⠊⠉⢑⠢⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠿⠈⠉⠀⠀⠀⠀⠀⠈⠹⣦⡶⠀⢀⣀⣠⠉⠲⢍⡙⠶⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢶⣿⣋⣠⣄⣀⣀⠀⣀⠀⠙⠢⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣆⣀⠀⠀⠀⠀⠈⠙⠢⣄⡀⠑⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣏⠉⠑⠲⢤⣄⡀⠀⠀⠙⢦⡈⢳⣄⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠟⠁⠀⣠⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠚⠉⠀⠀⠀⠀⠀⣈⣳⡄⠑⢦⡈⠙⠶⣄⠀⠀⠉⠳⣝⢧⡀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠏⠀⣠⡾⡿⠂⠀⠀⠀⠀⠀⠀⣠⣶⠟⠁⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠙⠆⠀⡌⠢⡀⠀⠙⠶⡀⠠⡈⢻⣿⡄⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⣠⡾⠋⡴⠁⠀⠀⠀⡀⢀⣴⣿⡟⠁⠀⠀⠀⠀⠀⠀⢴⠟⠀⠀⢠⡄⠀⡀⠀⠸⡀⠀⠀⠈⠢⡀⠀⡘⢦⠙⢿⡄⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⡋⢀⡼⠁⠀⢰⠀⠔⠁⣿⣿⠯⣤⡀⠀⢸⠇⠀⠀⢀⡎⠀⠀⠀⣸⠁⠀⣷⡀⠀⣿⠢⣰⡀⠠⣝⣦⡓⠀⠀⠚⣷⡀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢠⣿⠟⣵⣿⠁⠀⠀⣾⠀⢀⡼⠋⠀⢀⣸⠃⢠⡏⠀⠀⣰⠟⠁⠀⠀⢠⡿⠀⠀⡇⢷⠀⣿⠀⠙⢷⡜⣇⠈⠛⢶⣄⠀⠘⣷⡀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣸⠏⣼⢷⠃⠀⠀⢸⡟⢠⣾⡀⡀⠘⡽⠃⣠⡞⠀⠀⣰⠋⠀⠀⠀⢃⣾⠁⠀⠀⠃⠀⠇⢹⡇⠰⣠⡹⣞⢧⡲⢤⣽⡄⠀⠘⣧⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢰⣿⣿⠃⡏⠀⠀⠀⢸⠇⡸⢿⠟⠁⣾⢁⡴⠃⣄⠀⢰⠃⠀⠀⠀⠀⢨⡿⠀⠀⠀⠀⠀⠀⠀⣇⠀⢹⡇⠙⢧⣿⠀⠻⡇⢠⠀⢸⡀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣿⡏⢸⠃⠀⣰⠄⢸⠀⣇⠈⣀⣠⣿⠋⠀⢸⣇⠀⣿⠀⠀⠀⠀⠀⣼⠃⠀⠀⠀⠀⠀⠀⣰⣿⠀⢰⡇⠀⠈⢷⣄⣰⠇⠸⡇⢸⡇\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢸⠁⡾⠀⣼⢿⡀⣿⠀⠈⠋⠉⣅⡿⠀⣰⡏⢸⡀⣿⡄⢰⠀⠀⣸⠃⠀⠀⠀⡄⠀⣼⢢⣿⣿⡇⢸⡇⠀⠀⣤⡽⢋⠀⢠⣇⣸⡇\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⣼⡏⠸⡇⣇⠀⠀⠀⣸⣿⣧⣰⡏⠀⠈⣧⡇⢿⣾⠀⢠⡟⠀⠀⢀⣼⠃⣸⣃⣼⢫⣿⡇⣾⠃⠀⢰⡿⠃⣼⠀⣸⢸⣿⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠘⣇⣼⢻⡇⠀⢻⢿⠀⠀⢀⣿⣿⣿⣿⣏⣙⡓⢿⣷⢼⣧⣠⡞⠀⢠⣴⣾⢃⣰⣿⣿⣧⣼⣿⣿⡿⠐⣠⡿⣁⣼⣿⢠⡏⢸⣿⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠸⣿⣾⣿⡀⠘⣿⡆⠀⣸⡟⢿⢿⣿⣿⣿⡛⠿⣿⡀⣯⣿⢃⣴⣿⣿⢃⣰⣿⣿⣿⠶⠿⣿⣿⣇⣼⣿⣷⣿⣿⣿⡿⠁⢸⡏⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢿⣿⣧⡹⣿⣇⢰⣿⣷⡉⠀⠛⠿⠿⠃⠀⠈⠁⣿⣷⡟⣿⢿⣿⣿⡿⣿⣿⣿⠇⠀⣹⣿⣿⣿⣿⣿⠋⣹⡿⠃⠀⠘⠁⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣿⣷⡜⢿⣸⣿⢿⣷⣄⠀⠀⠀⠀⠀⢀⣾⣿⠋⠼⠉⣼⠿⠋⠀⠈⠉⠉⠀⣨⣿⣿⣿⣿⣿⣿⢀⡿⠁⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣿⣾⣿⣿⣦⡹⣿⠷⣤⡀⠀⠀⠺⠟⠀⠀⢀⠞⠁⠀⠀⠀⠀⢀⣢⣿⣿⣿⣿⣿⣿⣿⣿⠈⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⣽⣿⣿⣷⣉⡻⢾⣷⣄⠈⠉⠀⠀⠀⠀⠀⠀⠰⡂⠀⠀⠒⠊⠉⣉⣵⣿⣿⣿⣿⣿⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣿⠿⠛⠉⠀⠀⠉⠙⠻⢿⣶⢄⣀⠀⠀⠀⠀⢠⠞⠁⢀⣀⣤⣶⣿⣽⣿⣿⣿⣿⣿⡿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠞⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⣯⡛⠒⠒⠒⠒⠒⠛⠀⠀⠀⣠⣴⣿⣿⣿⣿⣿⣿⡿⢡⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⠒⠀⠀⠀⠀⢀⣠⣶⣿⣿⣿⣿⣿⡿⠋⣸⡟⠁⠘⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⣠⠟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣽⣷⣄⣀⣠⠴⠛⠋⢹⠟⣱⣿⠟⠋⠀⠀⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⢀⡠⠶⣻⡏⠀⠀⠀⢀⣤⠤⢤⡀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣧⠀⠀⠀⠀⠀⠰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢀⡖⠉⠀⣰⣿⠃⠀⡇⣰⣟⢻⣿⣟⣿⣧⠀⠀⠀⠀⠀⠀⠀⣾⡏⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⢠⠞⠀⠀⣸⣻⡏⠀⠀⣧⢻⡞⢻⠽⣯⣉⡟⠀⠀⠀⠀⠀⠀⣰⣯⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣰⣿⣿⡇⠀⠀⣿⣦⠛⠶⠤⠼⠋⢁⠀⠀⠀⠀⠀⡰⣯⣿⡷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣦⣤⣤⢾⣿⣿⣿⡇⠀⠀⠘⣿⡛⠛⠂⠀⠀⠀⠀⠀⠀⠀⠋⢀⣼⣿⣷⣿⡝⠲⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣀⡀⠀⣼⣿⣿⣿⡇⠀⠀⠀⠀⠙⠀⠀⠀⠀⠀⠀⠀⠀⣼⢇⣿⠏⢿⣿⣿⣧⡀⠀⠉⠓⠦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣸⡇⢀⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢿⣿⡏⠀⠈⢻⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀";
    }

    public static String asukaAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⡀⣤⡴⠞⠙⠻⢍⡉⠉⢻⣶⣤⣀⣀⣀⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣾⣿⠟⠉⠀⣈⡃⠁⠀⠀⠀⠀⠀⠀⠀⠀⠉⣻⣿⣿⣿⣿⡤⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠖⠋⣩⠟⣋⠿⠚⠁⠀⠀⡞⠀⡀⠀⠀⠉⡀⠀⠀⠀⡈⠳⣄⠀⠈⡉⠛⠿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡾⢻⠟⠁⢠⠆⡰⠀⢸⡏⠀⢧⠀⠀⡆⠉⢦⡀⠀⠀⠀⠀⠙⢦⡉⠲⣄⠈⠑⢄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⢠⢃⡔⡰⢁⠈⢀⠀⣸⣇⠀⢸⡄⠀⠘⣆⠀⢳⡀⠀⠈⢦⠀⠀⢿⣄⠈⢻⡓⢮⣧⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠰⡄⠀⠀⠀⠀⠀⢀⣿⠛⣿⠁⣼⠄⢸⢀⣿⣿⣆⢸⡿⣧⠀⠸⣆⠈⡟⢦⣀⠈⣧⠀⠀⢻⣶⣄⢳⣌⢿⣗⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣆⠀⠀⠙⡄⠀⠀⠀⠀⠸⠇⠘⡇⣸⣿⠀⣾⣼⠦⠿⣿⣶⣇⠙⣷⡀⢿⣆⣇⣠⣽⣷⣿⠷⣄⠈⢿⣟⠳⣿⠘⡎⠆⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠈⢣⡀⠀⠘⡄⢃⠀⠀⠀⠀⣰⣿⡟⣇⠀⣿⡿⣠⡴⢻⣿⣿⠋⠉⠳⣼⡟⠋⣿⣿⡟⣿⠆⢹⣧⣸⡟⠀⠸⣇⡇⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣇⠀⠙⣄⠀⢱⢸⡄⠀⠀⢀⡟⢉⡇⠹⣴⣿⣧⠀⠁⠀⠁⠀⠀⣼⠀⠈⠁⠀⠀⠀⠀⠀⠀⢸⣿⣿⡇⠀⡄⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣿⣧⡀⠈⣳⡄⠋⠀⠀⠀⠘⠀⢸⠀⡇⢻⣿⠾⡅⠀⠀⠀⠀⠀⣈⣀⡀⠀⠀⠀⠀⠀⠀⠀⣿⠉⣽⡇⠀⢇⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⡄⠙⢿⣾⡟⢀⣤⡄⠀⠀⠀⠀⡇⠈⡁⡆⠘⣦⣵⡀⠀⠀⠀⠀⣿⣿⣿⡄⠀⠀⠀⠀⣴⣾⣥⢾⡿⣷⠀⠸⡀⢿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠻⣆⠈⣿⢣⠸⡀⢱⠀⠀⠀⢰⠁⠀⡇⣹⠀⣿⠁⢿⣦⡀⠀⠀⠹⣿⣿⡇⠀⠀⠀⣴⠋⣸⡇⢸⠀⣿⠀⠀⢷⠘⡇⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠉⡿⠉⠻⢧⡇⢸⡇⠀⠀⡞⠀⠀⡇⣿⡇⣿⠀⣼⡇⡟⠢⡀⠀⠙⠋⠀⠀⣠⡞⠁⢸⣹⠃⢸⡀⢸⡆⠀⠘⣇⠹⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⣰⢁⣸⠖⢤⢹⡄⢧⠀⢠⠇⠀⠀⠃⡿⠃⢸⠀⣿⠀⣿⠀⢹⡷⣤⣤⣴⣛⣁⡇⢀⠇⣾⠀⢸⡇⠀⢷⠀⠀⠘⡄⢃⠀⠀⠀⠀⠀⠀\n" +
                "⢀⡞⠋⠀⣰⡀⢻⠀⠈⣇⣼⡏⠀⠀⢸⡇⠀⠸⣄⡿⠀⢹⣇⢈⡟⠛⠛⠛⠛⠛⠳⣼⣰⠿⢦⣼⣧⡤⠼⣦⣀⠀⠀⠸⡆⠀⠀⠀⠀⠀\n" +
                "⠸⡆⠀⠹⠛⢷⣸⠀⠀⣿⣿⠁⠀⠀⢸⣇⣀⣠⡿⠗⠚⣺⡿⠟⢷⠀⠀⠀⠀⠀⢶⡉⣠⣶⠖⠉⠀⠀⠀⠀⠈⠑⢦⡀⢹⡀⠀⠀⠀⠀\n" +
                "⠀⠘⢆⠀⠀⠀⠙⡀⡼⣹⢻⠀⡠⠚⠁⠀⠀⢯⣤⣞⡟⠳⠤⣄⡜⠀⢀⣠⠤⠔⠊⢹⢃⡟⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢦⣧⠀⠀⠀⠀\n" +
                "⠀⠀⢈⡗⠀⠀⠀⡼⢁⡏⣆⡜⠁⠀⠀⠀⠀⠀⢸⢹⠇⠀⠀⠈⠒⠐⠋⠁⠀⠀⠀⡞⣸⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⣆⠀⠀⠀\n" +
                "⠀⢀⡾⠁⠀⠀⡸⠁⣸⠀⡿⠀⠀⠀⠀⠀⠀⠀⡄⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⢣⠏⠀⠀⠀⠀⠀⣀⣠⠄⠀⠀⠀⠀⠀⠈⠑⢄⡀\n" +
                "⢀⡜⠀⠀⠀⢰⠃⠀⣇⡜⠁⠀⠀⠀⠐⣖⠀⢸⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠞⢸⡀⠀⠀⠀⣠⠞⠻⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠉\n" +
                "⠎⠀⠀⠀⢠⠇⠀⡰⠋⠀⠀⠀⠀⠀⢀⣼⣷⡏⠀⠙⠢⡀⠀⠀⠀⠀⡠⠒⠁⠀⠀⠀⠙⢦⡀⠀⡟⠀⢀⡇⠈⢳⣤⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⣀⡏⡠⠊⠀⠀⠀⠀⠀⢀⣠⢞⣿⠋⠀⠀⠀⠀⠘⢦⣀⡰⠊⠀⠀⠀⠀⠀⠀⠀⠀⠙⣄⠃⢀⣾⠁⠀⠘⡇⠉⡲⢤⣄⠀⠀⠀";
    }

    public static String shrekAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⣤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⠀⠀⠀⢀⣴⠟⠉⠀⠀⠀⠈⠻⣦⡀⠀⠀⠀⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿⣷⣀⢀⣾⠿⠻⢶⣄⠀⠀⣠⣶⡿⠶⣄⣠⣾⣿⠗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠉⢻⣿⣿⡿⣿⠿⣿⡿⢼⣿⣿⡿⣿⣎⡟⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡟⠉⠛⢛⣛⡉⠀⠀⠙⠛⠻⠛⠑⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣧⣤⣴⠿⠿⣷⣤⡤⠴⠖⠳⣄⣀⣹⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⣀⣟⠻⢦⣀⡀⠀⠀⠀⠀⣀⡈⠻⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⡿⠉⡇⠀⠀⠛⠛⠛⠋⠉⠉⠀⠀⠀⠹⢧⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⡟⠀⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠃⠀⠈⠑⠪⠷⠤⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣾⣿⣿⣿⣦⣼⠛⢦⣤⣄⡀⠀⠀⠀⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠑⠢⡀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠲⠖⠛⠻⣿⡿⠛⠉⠉⠻⠷⣦⣽⠿⠿⠒⠚⠋⠉⠁⡞⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢦⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣾⠛⠁⠀⠀⠀⠀⠀⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠤⠒⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢣⠀⠀⠀\n" +
                "⠀⠀⠀⠀⣰⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣑⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⠀⠀\n" +
                "⠀⠀⠀⣰⣿⣁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣧⣄⠀⠀⠀⠀⠀⠀⢳⡀⠀\n" +
                "⠀⠀⠀⣿⡾⢿⣀⢀⣀⣦⣾⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⣫⣿⡿⠟⠻⠶⠀⠀⠀⠀⠀⢳⠀\n" +
                "⠀⠀⢀⣿⣧⡾⣿⣿⣿⣿⣿⡷⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⢀⡴⢿⣿⣧⠀⡀⠀⢀⣀⣀⢒⣤⣶⣿⣿⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                "⠀⠀⡾⠁⠙⣿⡈⠉⠙⣿⣿⣷⣬⡛⢿⣶⣶⣴⣶⣶⣶⣤⣤⠤⠾⣿⣿⣿⡿⠿⣿⠿⢿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                "⠀⣸⠃⠀⠀⢸⠃⠀⠀⢸⣿⣿⣿⣿⣿⣿⣷⣾⣿⣿⠟⡉⠀⠀⠀⠈⠙⠛⠻⢿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                "⠀⣿⠀⠀⢀⡏⠀⠀⠀⢸⣿⣿⣿⣿⣿⣿⣿⠿⠿⠛⠛⠉⠁⠀⠀⠀⠀⠀⠉⠠⠿⠟⠻⠟⠋⠉⢿⣿⣦⡀⢰⡀⠀⠀⠀⠀⠀⠀⠁\n" +
                "⢀⣿⡆⢀⡾⠀⠀⠀⠀⣾⠏⢿⣿⣿⣿⣯⣙⢷⡄⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⠀⠀⠀⠀⢀⣤⣿⣻⢿⣷⣀⣷⣄⠀⠀⠀⠀⢸⠀\n" +
                "⢸⠃⠠⣼⠃⠀⠀⣠⣾⡟⠀⠈⢿⣿⡿⠿⣿⣿⡿⠿⠿⠿⠷⣄⠈⠿⠛⠻⠶⢶⣄⣀⣀⡠⠈⢛⡿⠃⠈⢿⣿⣿⡿⠀⠀⠀⠀⠀⡀\n" +
                "⠟⠀⠀⢻⣶⣶⣾⣿⡟⠁⠀⠀⢸⣿⢅⠀⠈⣿⡇⠀⠀⠀⠀⠀⣷⠂⠀⠀⠀⠀⠐⠋⠉⠉⠀⢸⠁⠀⠀⠀⢻⣿⠛⠀⠀⠀⠀⢀⠇\n" +
                "⠀⠀⠀⠀⠹⣿⣿⠋⠀⠀⠀⠀⢸⣧⠀⠰⡀⢸⣷⣤⣤⡄⠀⠀⣿⡀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡆⠀⠀⠀⠀⡾⠀⠀⠀⠀⠀⠀⢼⡇\n" +
                "⠀⠀⠀⠀⠀⠙⢻⠄⠀⠀⠀⠀⣿⠉⠀⠀⠈⠓⢯⡉⠉⠉⢱⣶⠏⠙⠛⠚⠁⠀⠀⠀⠀⠀⣼⠇⠀⠀⠀⢀⡇⠀⠀⠀⠀⠀⠀⠀⡇\n" +
                "⠀⠀⠀⠀⠀⠀⠻⠄⠀⠀⠀⢀⣿⠀⢠⡄⠀⠀⠀⣁⠁⡀⠀⢠⠀⠀⠀⠀⠀⠀⠀⠀⢀⣐⡟⠀⠀⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⢠⡇";
    }

    public static String sakuraAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡄⢣⠀⠀⢧⠀⢣⠀⠀⠀⠀⢰⠀⣂⠀⠀⠀⣀⠀⠈⢣⠀⠀⠀⢣⠱⣄⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈\n" +
                "⠀⡅⠀⠀⠀⠀⠀⠀⠀⠀⡇⢀⣣⠀⠘⡄⠈⢆⠀⠀⠀⠸⡀⠀⠀⢠⣾⣿⣧⡀⠀⢣⠀⠀⢸⠀⠈⣧⡀⠀⠀⠀⠀⣠⠂⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠘⠗⢣⡀⢳⠀⠈⢧⠀⠀⠀⡇⠀⠀⠘⢿⣿⠟⠁⠀⠀⢣⠀⠀⡄⠀⠀⠹⣄⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡧⠤⢄⣀⠑⡌⣇⠀⠈⢣⡀⠀⢳⠀⠀⠀⠀⠁⠀⠀⠉⠀⠀⢧⠀⡇⠀⠀⠀⠙⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢸⠀⠀⠀⠀⠀⠀⠀⠄⠀⡇⠀⠀⠀⠉⠛⢾⣦⣀⡀⠳⡀⠸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⢈⣇⣷⣴⠶⠒⠛⠉⢷⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⢳⣠⣔⣶⣖⣒⣤⣻⡍⠛⠷⢿⡄⢻⠀⠀⠀⠀⠀⠙⢶⣶⡟⠋⢙⣿⣶⣦⣭⣿⣋⣦⢷⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣿⡟⠉⡽⢉⡉⠻⣿⣦⠀⠀⠘⢮⡆⠀⠀⠀⠀⢠⡾⠋⠀⣴⠿⠟⢉⡉⢫⠙⣿⡟⠁⠱⡀⠀⠀⠀⠀⠀\n" +
                "⠀⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢛⢧⠀⢧⡈⠉⣠⠃⠹⡷⠀⠀⠀⠻⡄⠀⠀⠀⢸⠁⠀⣼⠏⠀⢦⣈⣠⠎⣴⠏⠀⠀⠀⢳⡀⠀⠀⠀⠀\n" +
                "⠀⣁⠀⠀⠀⠀⠐⢄⠀⠀⠀⠘⣯⡿⠶⢾⠿⣶⡶⠿⠃⠀⠀⠀⠀⠙⠂⠀⠀⠚⠀⠀⠛⠻⠶⠶⠶⠒⠛⠙⠃⠀⠀⠀⢀⣷⡀⠀⠀⠀\n" +
                "⠀⡟⡆⠀⠀⠀⠀⠈⠣⡀⠀⠀⠹⡁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠛⢳⡀⠀⠀\n" +
                "⠀⠁⢸⡀⠀⠀⣴⠀⠀⠙⢆⠀⠀⢣⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⡀⠀⠀⠀⢰⠃⣠⣫⣧⠀⠀\n" +
                "⠀⠀⠀⢣⠀⠀⠛⠀⠀⠀⠀⢳⣄⠈⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠂⠀⠀⠀⠀⢀⣿⣿⣽⣿⣿⢇⠀\n" +
                "⢸⠀⠀⠈⢧⠀⠀⠀⠀⠀⠀⠘⡆⠑⢄⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣾⣿⢫⣿⣿⣷⠼⡆\n" +
                "⢸⠀⠀⠀⠈⢦⠀⠀⠀⠀⠀⠀⠸⡀⠀⠙⠻⡄⠀⠀⠀⠀⠀⠐⢶⣶⣤⣤⣶⠖⠀⠀⠀⠀⠀⠀⠐⠂⠀⠀⢀⣾⢋⣼⣾⣿⡿⡫⠊⢹\n" +
                "⠈⠀⠀⠀⠀⢈⣧⠀⠀⠀⠀⠀⠀⢱⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⠿⠟⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣔⡱⠋⠀\n" +
                "⠀⡀⠀⠀⣴⣿⣾⣧⡀⠀⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣾⣽⣶⣿⢿⣿⡿⢧⠀⠀\n" +
                "⠀⡇⠀⢀⡏⠛⠻⠿⣷⡄⠀⠀⠀⠀⠈⢆⠀⠀⠀⠀⠀⠀⡀⠤⠴⠤⠤⠤⠶⠦⠤⠄⠀⠀⠀⠀⢠⣾⣿⢿⣿⠟⠁⣠⢪⠞⠀⣼⠀⠀\n" +
                "⠀⠇⠀⢸⠀⢼⡄⠀⣼⢹⢆⠀⠀⠀⠀⠈⢆⠀⠀⠀⠀⠀⠀⠀⠀⠒⠒⠒⠒⠒⠀⠀⠀⠀⢀⠴⣿⣿⣿⣿⠃⠀⣴⡇⡇⠠⣼⣿⠀⠀\n" +
                "⠀⢸⠀⢸⠀⣼⡇⠀⢹⢸⠈⣷⣀⠀⠀⠀⠈⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣴⣯⣾⣿⣿⣿⣿⠀⢼⠿⠇⡧⠀⣿⣿⠀⠀";
    }

    public static String yumekoAscii(){
        return "⣿⣿⣿⣿⣿⣿⡿⠋⠀⣰⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⣿⡟⣡⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣿⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⢯⡴⢣⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣿⣿⣿⣿⡟⢠⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⢸⣿⣿⣿⣿⢻⣿⣿⣿⣿⣿⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣿⣿⣿⠏⣴⡿⣿⣿⣿⣿⣿⣿⣿⣿⡟⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⣿⣿⣿⡟⢸⣿⣿⢿⣿⣿⣿⣿⣿⣿⡄⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣿⣿⠏⡼⢻⣼⣿⣿⣿⣿⣿⣿⣿⣿⡇⢻⣿⣿⣿⣿⣿⣿⠇⠀⠀⠟⣫⡵⠶⠛⠿⡿⣶⣌⣿⣿⣿⣿⣿⣷⡀⠀⠣⡀⠀⠀⠀⠀⠀⠀\n" +
                "⣿⡟⣼⠁⣾⣿⣿⣿⣿⣿⢻⣿⣿⣿⣇⣈⣹⡿⠛⠛⠉⠁⠀⠀⠀⡼⡿⠧⠴⠦⠤⠧⠼⠛⣿⣿⣿⣿⣿⣿⣿⡄⠀⠱⡀⠀⠀⠀⠀⠀\n" +
                "⡟⣸⠏⠀⣿⣿⣿⣿⣿⣿⡀⢻⣿⡿⠛⠋⠉⣻⡗⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⣿⣿⣿⣿⣿⣿⣿⠘⢆⠀⠐⡄⠀⠀⠀⠀\n" +
                "⢁⡏⠀⠀⣿⣿⣿⣿⣿⣿⣧⣿⠋⣧⠤⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢐⡟⢹⡇⣿⣿⣿⣿⣿⡇⠈⢧⠀⠘⡄⠀⠀⠀\n" +
                "⢸⠋⠀⠀⣿⣿⣿⣿⣿⣿⣿⠁⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠁⠀⣼⠁⣿⣿⣿⣿⣿⡇⠀⠈⢧⠀⠱⠀⠀⠀\n" +
                "⠚⠇⠀⠀⣿⣿⣿⣿⣿⣿⡿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⠃⠀⣿⣿⣿⣿⣿⣧⠀⠀⠀⢳⡀⠁⠀⠀\n" +
                "⢶⡏⠀⠀⣿⣸⣿⣿⣿⣿⡿⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠄⢀⣀⣠⣄⡠⠄⠀⠀⠀⠀⢰⠋⣿⣿⣿⣿⣿⠀⠀⠀⠀⠹⡄⠀⠀\n" +
                "⠰⠋⠀⠀⢹⣿⣿⣿⣿⣿⡿⣜⣄⠀⠀⠀⠀⠀⠀⠀⣀⣴⣿⠾⠛⢋⣩⡿⠉⠀⠀⠀⢀⠀⠀⣸⢛⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠹⡄⠀\n" +
                "⠀⠀⠀⠀⢈⣿⣿⣿⣿⣿⣷⡈⠻⢆⠀⠀⠀⠀⠀⠀⠀⠈⠉⡽⢍⡟⠏⠀⠀⠀⡰⠉⡏⠀⣠⣧⢼⣿⣿⣿⣿⣿⣷⠀⠀⠀⠀⠀⠹⡄\n" +
                "⢀⠀⠀⠀⡼⢻⣿⣿⣿⣿⣿⠙⠢⣍⣳⡀⠀⠀⠀⠀⠀⠀⠀⢇⠈⡄⠘⡄⠀⢰⠁⢠⢁⣴⠟⢀⣾⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠹\n" +
                "⠀⠀⠀⢠⠇⠈⣿⣿⣿⣿⣿⣇⠀⠀⠀⠙⠢⣀⠀⠀⠀⠀⠀⠈⠣⡱⡀⠘⣄⢸⠀⠐⣿⠋⠀⣾⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⢀⠏⠀⢰⣿⣿⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠙⢶⠦⣤⣄⣀⣀⣘⣧⠀⠈⢾⠀⠀⡇⠀⢰⣿⣿⣿⣿⣿⡇⢹⣿⣿⡇⠀⠀⠀⠀⠀\n" +
                "⠀⢀⡞⠀⠀⣾⣿⠹⣿⣿⣿⣿⣿⡀⠀⠀⠀⢶⠀⣼⢆⣀⠉⠙⠛⠛⠛⢣⡀⢸⠀⠀⡇⠀⣼⣿⣿⣿⣿⡿⠁⠀⢿⣿⣿⡀⠀⠀⠀⠀\n" +
                "⢀⡞⠀⠀⣸⣿⡏⠀⠙⣿⣿⣿⣿⣷⡀⠀⢰⣼⣰⡇⠀⠀⠉⠒⠢⢄⣀⣀⣵⣼⠀⠀⢡⢰⣿⣿⣿⣿⠛⠁⠀⠀⠈⢿⣿⣧⠀⠀⠀⠀\n" +
                "⠋⠀⠀⣰⣿⡟⠀⠀⠀⢸⣿⣿⣿⣿⣿⣶⣿⣿⣿⠀⢰⠋⠒⢄⠀⢀⣴⠉⣾⡇⠀⠀⠈⡏⠸⣏⠉⠁⠀⠀⢸⣦⡀⠈⢿⣿⣧⠀⠀⠀\n" +
                "⠀⠀⣰⣿⣿⢠⢈⣡⣴⣿⠗⢉⢿⣟⣿⣿⣿⣿⡏⠀⠀⢣⡀⢀⣳⣧⣬⢅⣿⠃⠀⠀⠀⢸⠀⢻⣷⣤⣀⣤⣼⣿⣷⣄⣸⣿⣿⣧⠀⠀\n" +
                "⢀⣼⣿⣿⣾⣿⣿⣿⣿⣿⡿⢿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⣱⡟⠋⠹⢿⠏⡘⠀⠀⠀⠀⡜⠀⠸⣿⣿⣿⡿⠿⣿⣿⣿⣿⣾⣿⣿⣷⡀\n" +
                "⠿⠛⠋⠉⠉⠁⠀⠀⠈⠉⠁⢠⣠⣿⣿⣿⣿⡿⠤⠤⠖⠁⣠⣷⠀⠀⠀⠠⠃⠀⠀⠀⢠⡗⢄⣀⣻⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀⠈⠉⠙\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⣿⣿⣿⠀⠀⢀⣴⣾⣿⣿⡆⠀⠀⠁⠀⠀⠀⠀⢸⣧⣄⠀⠘⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⣿⣿⣿⣿⣿⡇⢤⣾⣿⣿⠿⠋⠁⠙⣄⠀⠀⠀⠀⠀⠀⢸⠿⣿⣷⣤⣹⣿⣿⣿⣿⣷⣄⠀⠀⠀⠀⠀⠀";
    }

    public static String mitsuriAscii(){
        return "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡖⠶⣤⣤⡤⠤⠤⠤⢤⣀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣼⠁⡰⠁⣠⠉⠓⠸⠠⢒⣫⣽⡶⠶⣶⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⠞⣉⠤⢾⠀⠃⣸⠁⡄⠀⡇⠘⠋⣀⠈⠻⡷⡌⠳⡿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠴⢋⡔⠉⡠⠤⣼⡼⠀⡇⢰⠁⢰⠁⣞⣉⣉⠑⢦⡘⢿⢦⠘⠎⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⡞⠁⢀⡮⠖⣡⠔⠋⢹⠇⢰⠀⣼⠀⢸⢠⡏⠉⠙⠿⣟⣻⣎⢣⠑⣆⠀⢳⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢠⡟⠁⡏⠀⣰⠋⣠⠞⠁⠀⠀⣼⠀⢸⠀⡇⠀⠘⢸⠀⠀⠀⠀⠀⠙⢿⣦⢧⢸⡄⠀⢻⣦⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⢸⠃⢸⠀⡰⠁⡴⠁⠀⠀⢀⣠⣾⡆⢸⠀⡇⠀⡇⢸⠐⢶⣤⣀⡀⠀⠀⠙⣿⢺⡇⠀⠐⡏⢷⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⢀⣾⠀⠈⢰⠃⡼⠁⠀⠀⠾⠛⠋⠀⢻⣼⠀⣧⠀⣧⢸⠀⠀⠈⠙⠛⠂⠀⠀⠸⣶⡇⠀⠀⢣⠈⢿⠳⣄⠀⠀⠀⠀\n" +
                "⠀⠀⠀⢀⣴⢻⢿⠀⠀⡏⣸⠁⠀⠀⢀⣀⠀⠀⣀⠈⢿⠀⢸⠀⣿⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠹⡇⠀⡄⢸⠀⠘⡆⠈⠳⣄⠀⠀\n" +
                "⠀⠀⣠⢾⢃⣏⣾⠀⡜⠁⡏⠀⡠⢚⣉⣀⣤⣄⣈⠑⠺⣇⢸⡆⠿⣿⠀⠒⠉⠀⡀⢀⡖⠲⢤⡀⡇⠀⣇⠘⡆⠀⢻⡇⠀⠞⡆⠀\n" +
                "⠀⣼⡿⠛⣸⠏⢸⠀⠂⠀⡷⣋⣰⣾⠿⠟⠛⠛⠻⣷⡄⢮⠀⣧⡆⢿⠆⢀⣴⠾⠿⠿⢷⣶⣶⣯⣧⠀⡿⠀⡇⠀⡸⠀⡼⢠⠇⠀\n" +
                "⠀⢹⡗⠚⠁⣠⢿⡀⢠⢸⣧⣽⡿⢁⡔⠋⠉⠉⠓⣆⠙⠾⣧⠸⣧⠈⠀⠉⠀⡠⠖⠒⠶⢬⡙⢿⣿⡄⡇⢸⠃⢰⠃⡰⢁⡞⠀⠀\n" +
                "⠀⠀⠳⣤⠞⠁⠀⣧⢸⣸⡿⡟⠀⡞⠀⢀⠀⠀⠀⠈⣧⠀⠘⢧⠻⡄⠀⢠⠎⠀⠀⠀⠀⠀⠙⢶⢻⠁⣇⡎⠠⠃⢰⣡⠏⠀⠀⠀\n" +
                "⠀⠀⢀⡼⣦⠤⢤⡼⣾⣿⡇⠃⠀⠇⠀⠿⠿⠿⠃⠀⡼⠀⠀⠀⠉⠃⠀⠘⠀⠰⠿⣷⡶⠂⠀⢸⢹⣶⣟⡤⠄⠀⠸⢧⣄⡀⠀⠀\n" +
                "⠀⣠⣏⠀⢻⢀⠎⠀⣿⣿⠃⠀⠀⠙⠦⢤⡤⠤⠴⠚⠁⠀⠀⠀⠀⠀⠀⠐⢦⣀⠀⠀⠀⢀⡠⢋⣼⡟⠉⡇⠀⢀⠀⠀⠈⠻⣦⡀\n" +
                "⣰⡇⠹⡄⢈⡇⠀⡜⢀⡟⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠑⠒⠊⠉⣠⢾⠁⢧⠀⢃⠀⣠⠄⢀⠆⠀⠘⡇\n" +
                "⡏⢳⡀⢳⠀⢸⡘⠀⡜⢠⢻⢀⡤⠊⠔⡧⠒⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣖⠔⢻⠄⠀⣰⡇⠸⡄⠘⡆⠘⠞⢁⡴⠃⠀⠀⢰⠇\n" +
                "⢻⡄⢳⡀⢣⠀⢧⠎⢀⠃⢸⡀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡇⢸⠀⢣⠀⢱⠀⠐⠋⡠⠀⡀⢀⡞⠀\n" +
                "⠀⠹⣆⠑⠀⠃⠈⠀⠎⣠⠋⢳⡀⠀⠀⠀⠀⠀⠀⠈⠓⠦⣄⣠⠤⠚⠁⠀⠀⠀⠀⠀⢸⡇⠈⣇⠈⢇⠀⠣⠒⠋⣠⠎⢠⠞⠀⠀\n" +
                "⠀⠀⠈⠻⣶⣄⡤⠖⢋⡥⠂⠈⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣧⡀⠘⣆⠈⠀⠀⠠⠞⢁⡴⠃⠀⠀⠀\n" +
                "⠀⢀⡴⠒⢏⠙⢆⠚⠁⣀⠤⠂⠙⢿⢦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡤⣾⢻⠷⣄⡀⠀⣀⠤⢠⡶⣯⣀⠀⠀⠀⠀\n" +
                "⢀⣾⠻⡄⠀⠳⣌⠻⡉⣀⠤⠀⢀⣼⠀⠉⠛⠦⣤⣀⡀⠀⠀⠀⠀⢀⣀⣠⣴⣾⠏⡰⢃⣎⣤⠚⣏⠉⢁⠔⠉⢀⠔⠙⣷⠀⠀⠀\n" +
                "⢸⡟⢆⠙⣄⠀⠈⠣⡌⠁⢉⣠⠞⠁⠀⠀⠀⣠⡏⠉⠙⠛⠒⠒⠚⠉⠁⠀⠸⣿⣾⣤⡟⠋⠸⡄⠸⡖⠉⢀⠔⠁⢀⠀⢸⠀⠀⠀\n" +
                "⠀⠙⣮⣳⡈⠓⠄⠀⠀⠉⠛⠶⣄⠀⣀⣴⣊⣵⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡹⠟⡿⡆⢸⠀⢧⠀⢱⠔⠁⢀⡴⠋⢀⡟⠀⠀⠀\n" +
                "⢠⡞⣁⢻⡓⠚⢷⡀⠀⡠⠂⠁⠈⠻⡅⢸⠟⢺⠏⠀⠀⠀⠀⠀⠀⠀⣠⠖⠉⣠⢾⠁⢳⠈⡇⠈⣆⠀⠷⠒⡁⠀⢠⡞⠀⠀⠀⠀\n" +
                "⢸⡄⠘⣄⠙⣄⠀⠳⣎⣠⠔⠁⠀⠀⡅⠘⣇⢸⣆⠀⠀⠀⠀⢀⡴⠋⣀⠴⠚⡆⢸⠀⠀⢧⡘⠄⠈⣂⠀⠈⢀⠴⠋⠀⠀⠀⠀⠀";
    }


}
